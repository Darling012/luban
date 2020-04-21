package com.cntytz.web.exception;

/**
 * @program: luban
 * @description:
 * @author: ling
 * @create: 2020-04-11 22:15
 **/
public class UserException extends RuntimeException {
    private static final long serialVersionUID = -3754700886552020657L;


    public UserException(String message) {
        super(message);
    }
}
