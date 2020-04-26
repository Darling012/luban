package com.cntytz.web.exception;

import com.cntytz.yunti.exception.ServiceException;
import com.cntytz.yunti.exception.enums.HttpStateCode;

/**
 * @program: luban
 * @description:
 * @author: ling
 * @create: 2020-04-11 22:15
 **/
public class UserException extends ServiceException {
    private static final long serialVersionUID = -3754700886552020657L;

    public UserException(String message) {
        super(message);
    }

    public UserException(HttpStateCode resultCode) {
        super(resultCode);
    }

    public UserException(HttpStateCode resultCode, String msg) {
        super(resultCode, msg);
    }

    public UserException(HttpStateCode resultCode, Throwable cause) {
        super(resultCode, cause);
    }

    public UserException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
