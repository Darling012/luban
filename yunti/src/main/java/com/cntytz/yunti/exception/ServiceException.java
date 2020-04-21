package com.cntytz.yunti.exception;

import com.cntytz.yunti.exception.enums.GlobalExceptionCode;
import lombok.Getter;

/**
 * 业务基础异常
 *
 * @author Darling
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 2359767895161832954L;

    @Getter
    private final GlobalExceptionCode resultCode;

    public ServiceException(String message) {
        super(message);
        this.resultCode = GlobalExceptionCode.FAILURE;
    }

    public ServiceException(GlobalExceptionCode resultCode) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

    public ServiceException(GlobalExceptionCode resultCode, String msg) {
        super(msg);
        this.resultCode = resultCode;
    }

    public ServiceException(GlobalExceptionCode resultCode, Throwable cause) {
        super(cause);
        this.resultCode = resultCode;
    }

    public ServiceException(String msg, Throwable cause) {
        super(msg, cause);
        this.resultCode = GlobalExceptionCode.FAILURE;
    }

    /**
     * for better performance
     *
     * @return Throwable
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }
}
