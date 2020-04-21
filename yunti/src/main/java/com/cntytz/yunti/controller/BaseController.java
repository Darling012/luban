package com.cntytz.yunti.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cntytz.yunti.body.pojo.PageResult;
import com.cntytz.yunti.dao.QueryBuilder;
import com.cntytz.yunti.body.pojo.PageCondition;
import com.cntytz.yunti.controller.copier.BaseCopier;
import com.cntytz.yunti.dao.BaseEntity;
import com.cntytz.yunti.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: luban
 * @description: 提供通用功能的controller
 * @author: ling
 * @create: 2020-04-14 17:07
 **/
@Slf4j
public class BaseController<T extends BaseEntity, V extends Serializable, P extends V> {

    private BaseService<T> baseService;

    private BaseCopier<T, V> baseCopier;

    public BaseController(BaseService<T> baseService, BaseCopier<T, V> baseCopier) {
        this.baseService = baseService;
        this.baseCopier = baseCopier;
    }

    @GetMapping("{id}")
    public V queryById(@PathVariable String id) {
        return baseCopier.entityToVo(baseService.getEntityById(id));
    }

    @PostMapping
    public void saveEntity(@RequestBody V vo) {
        baseService.createEntity(baseCopier.voToEntity(vo));
    }

    @PutMapping
    public void updateById(@RequestBody V vo) {
        baseService.updateEntityById(baseCopier.voToEntity(vo));
    }

    @DeleteMapping
    public void deleteById(@PathVariable String id) {
        baseService.deleteEntityById(id);
    }

    @PostMapping("page")
    public PageResult<V> queryByPageCondition(@RequestBody PageCondition<P> pageCondition) {
        Wrapper<T> queryWrapper = QueryBuilder.toQueryWrapper(pageCondition.getCondition());
        Page<T> page = new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize());
        IPage<T> iPage = baseService.queryByPageCondition(queryWrapper, page);
        PageResult<V> pageResult = new PageResult<>();
        pageResult.setPageNo((int) iPage.getCurrent());
        pageResult.setPageSize((int) iPage.getSize());
        pageResult.setTotalCount((int) iPage.getTotal());
        pageResult.setTotalPage((int) iPage.getPages());
        List<T> list = iPage.getRecords();
        pageResult.setList(list.stream().map(t -> baseCopier.entityToVo(t)).collect(Collectors.toList()));
        return pageResult;
    }
}
