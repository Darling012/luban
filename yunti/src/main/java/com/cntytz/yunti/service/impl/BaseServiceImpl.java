package com.cntytz.yunti.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cntytz.yunti.exception.EntityCrudException;
import com.cntytz.yunti.exception.enums.GlobalExceptionCode;
import com.cntytz.yunti.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @program: luban
 * @description: crud基础实现
 * @author: ling
 * @create: 2020-04-13 22:24
 **/
@Slf4j
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {
    @Override
    public BaseMapper<T> getMapper() {
        return baseMapper;
    }

    @Override
    public T getEntityById(Serializable id) {
        return Optional.of(super.getById(id)).orElseThrow(() -> new EntityCrudException(GlobalExceptionCode.ADD_ENTITY_ERROR));
    }

    @Override
    public void createEntity(T entity) {
        boolean flag = super.save(entity);
        if (!flag) {
            throw new EntityCrudException(GlobalExceptionCode.ADD_ENTITY_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createEntities(Collection<T> entityList) {
        // 判断数据库类型
        for (T entity : entityList) {
            createEntity(entity);
        }
    }

    @Override
    public void updateEntityById(T entity) {
        boolean flag = super.updateById(entity);
        if (!flag) {
            throw  new EntityCrudException(GlobalExceptionCode.UPDATE_ENTITY_ERROR);
        }
    }

    @Override
    public void updateEntityById(T entity, Wrapper<T> updateCriteria) {
        boolean flag = super.update(entity, updateCriteria);
        if (!flag) {
            throw  new EntityCrudException(GlobalExceptionCode.UPDATE_ENTITY_ERROR);
        }
    }

    @Override
    public void updateEntityById(Wrapper<T> updateWrapper) {
        boolean flag = super.update(null, updateWrapper);
        if (!flag) {
            throw  new EntityCrudException(GlobalExceptionCode.UPDATE_ENTITY_ERROR);
        }
    }

    @Override
    public void updateEntities(Collection<T> entityList) {
        boolean flag = super.updateBatchById(entityList);
        if (!flag) {
            throw  new EntityCrudException(GlobalExceptionCode.UPDATE_ENTITY_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrUpdateEntities(Collection<T> entityList) {
        boolean flag = super.saveOrUpdateBatch(entityList, 1000);
        if (!flag) {
            throw  new EntityCrudException(GlobalExceptionCode.UPDATE_ENTITY_ERROR);
        }
    }

    @Override
    public void deleteEntityById(Serializable id) {
        boolean flag = super.removeById(id);
        if (!flag) {
            throw  new EntityCrudException(GlobalExceptionCode.DELETE_ENTITY_ERROR);
        }
    }

    @Override
    public void deleteEntitiesByIds(Wrapper<T> queryWrapper) {
        boolean flag = super.remove(queryWrapper);
        if (!flag) {
            throw new EntityCrudException(GlobalExceptionCode.DELETE_ENTITY_ERROR);
        }
    }

    @Override
    public void deleteEntitiesByIds(Collection<? extends Serializable> entityIds) {
        boolean flag = super.removeByIds(entityIds);
        if (!flag) {
            throw new EntityCrudException(GlobalExceptionCode.DELETE_ENTITY_ERROR);
        }
    }

    @Override
    public List<T> getEntityList(Wrapper<T> queryWrapper) {
        return getEntityList(queryWrapper, null);
    }

    @Override
    public List<T> getEntityList(Wrapper<T> queryWrapper, IPage<T> iPage) {
        if (iPage != null) {
            IPage<T> page = super.page(iPage, queryWrapper);
            // 如果重新执行了count进行查询，则更新pagination中的总数
            if (page.isSearchCount()) {
                page.setTotal(page.getTotal());
            }
            return page.getRecords();
        } else {
            List<T> list = super.list(queryWrapper);
            if (list == null) {
                list = Collections.emptyList();
            } else if (list.size() > 1000) {
                log.warn("单次查询记录数量过大，返回结果数={}", list.size());
            }
            return list;
        }
    }

    @Override
    public List<T> getEntityListByIds(List<Long> ids) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return getEntityList(queryWrapper);
    }

    @Override
    public IPage<T> queryByPageCondition(Wrapper<T> queryWrapper, IPage<T> iPage) {
        return super.page(iPage, queryWrapper);
    }
}
