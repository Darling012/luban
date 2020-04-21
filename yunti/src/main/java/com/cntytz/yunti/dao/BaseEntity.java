package com.cntytz.yunti.dao;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: luban
 * @description: 实体基类
 * @author: ling
 * @create: 2020-04-14 14:16
 **/
@Data
public class BaseEntity {
    /**
     * 创建时间
     **/
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 创建人
     **/
    private Long createId;
    /**
     * 更新时间
     **/
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    /**
     * 更新人
     **/
    private Long updateId;
    /**
     * 删除标识 0正常 1删除
     **/
    @JSONField(serialize = false)
    private Integer flag;
}
