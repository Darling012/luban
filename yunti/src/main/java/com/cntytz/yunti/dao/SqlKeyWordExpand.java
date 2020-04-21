package com.cntytz.yunti.dao;

/**
 * @author Darling
 */

public enum SqlKeyWordExpand {
    /**
     * 相等，默认
     */
    EQ,
    /**
     * IN
     */
    IN,
    /**
     * 以xx起始
     */
    STARTWITH,
    /**
     * LIKE
     */
    LIKE,
    /**
     * 等同LIKE
     */
    CONTAINS,
    /**
     * 大于
     */
    GT,
    /**
     * 大于等于
     */
    GE,
    /**
     * 小于
     */
    LT,
    /**
     * 小于等于
     */
    LE,
    /**
     * 介于-之间
     */
    BETWEEN,
    /**
     * 介于之后
     */
    BETWEEN_BEGIN,
    /**
     * 介于之前
     */
    BETWEEN_END
}
