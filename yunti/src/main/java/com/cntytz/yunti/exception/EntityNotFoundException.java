package com.cntytz.yunti.exception;

/**
 * @program: luban
 * @description: 实体未查询到
 * @author: ling
 * @create: 2020-04-14 22:19
 **/
public class EntityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -2257293122034202488L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
