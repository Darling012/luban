package com.cntytz.yunti.dao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BindQuery {
    /**
     * 查询条件
     *
     * @return
     */
    SqlKeyWordExpand keyWord() default SqlKeyWordExpand.EQ;

    /**
     * 数据库字段，默认为空，自动根据驼峰转下划线
     *
     * @return
     */
    String field() default "";

    /**
     * 忽略该字段
     *
     * @return
     */
    boolean ignore() default false;
}
