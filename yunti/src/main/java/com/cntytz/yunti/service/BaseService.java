package com.cntytz.yunti.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Darling
 */
public interface BaseService<T> {

    /**
     * 获取对应 entity 的 BaseMapper
     *
     * @return BaseMapper
     */
    BaseMapper<T> getMapper();

    /**
     * 获取Entity实体
     *
     * @param id 主键
     * @return entity
     */
    T getEntityById(Serializable id);

    /**
     * 创建Entity实体
     *
     * @param entity
     * @return true:成功, false:失败
     */
    void createEntity(@NotNull T entity);

    /***
     * 批量创建Entity
     * @param entityList 实体对象列表
     * @return true:成功, false: 失败
     */
    void createEntities(@NotEmpty Collection<T> entityList);

    /**
     * 更新Entity实体
     *
     * @param entity
     * @return
     */
    void updateEntityById(@NotNull T entity);

    /**
     * 更新Entity实体（更新符合条件的所有非空字段）
     *
     * @param entity
     * @param updateCriteria
     * @return
     */
    void updateEntityById(T entity, Wrapper<T> updateCriteria);

    /**
     * 更新Entity实体（仅更新updateWrapper.set指定的字段）
     *
     * @param updateWrapper
     * @return
     */
    void updateEntityById(Wrapper<T> updateWrapper);

    /**
     * 批量更新entity
     *
     * @param entityList
     * @return
     */
    void updateEntities(Collection<T> entityList);

    /**
     * 批量创建或更新entity（entity.id存在则新建，否则更新）
     *
     * @param entityList
     * @return
     */
    void createOrUpdateEntities(Collection<T> entityList);

    /**
     * 根据主键删除实体
     *
     * @param id 主键
     * @return true:成功, false:失败
     */
    void deleteEntityById(Serializable id);

    /**
     * 按条件删除实体
     *
     * @param queryWrapper
     * @return
     * @throws Exception
     */
    void deleteEntitiesByIds(Wrapper<T> queryWrapper);

    /**
     * 批量删除指定id的实体
     *
     * @param entityIds
     * @return
     * @throws Exception
     */
    void deleteEntitiesByIds(Collection<? extends Serializable> entityIds);

    /**
     * 获取指定条件的Entity集合
     *
     * @param queryWrapper
     * @return
     * @throws Exception
     */
    List<T> getEntityList(Wrapper<T> queryWrapper);

    /**
     * 获取指定条件的Entity集合
     *
     * @param queryWrapper
     * @param iPage
     * @return
     * @throws Exception
     */
    List<T> getEntityList(Wrapper<T> queryWrapper, IPage<T> iPage);

    /**
     * 获取指定条件的Entity集合
     *
     * @param ids
     * @return
     */
    List<T> getEntityListByIds(List<Long> ids);

     IPage<T> queryByPageCondition(Wrapper<T> queryWrapper, IPage<T> iPage);
}
