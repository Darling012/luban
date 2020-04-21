package com.cntytz.yunti.body.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: demo
 * @description:
 * @author: ling
 * @create: 2020-03-10 21:10
 **/
@AllArgsConstructor
@Data
public class RespResult<T> implements Serializable {
    public static final int SUCCESS = 0;
    public static final int UNKNOWN_ERROR = -1;
    private static final long serialVersionUID = -1607997078886917048L;
    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体的内容.
     */
    private T data;


    public static <T> RespResult<T> success(T data) {
        return new RespResult<>(SUCCESS, "成功", data);
    }

    public static <T> RespResult<T> fail(int code, String msg) {
        return new RespResult<>(code, msg, null);
    }

    public static <T> RespResult<T> unknownError(String msg) {
        return new RespResult<>(UNKNOWN_ERROR, msg, null);
    }
}
