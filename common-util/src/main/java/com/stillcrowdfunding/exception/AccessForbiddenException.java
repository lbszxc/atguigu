package com.stillcrowdfunding.exception;

/**
 * 表示没有登录就访问受保护资源抛出的异常
 * @author Administrator
 * @date 2020/6/18 18:31
 * @description
 **/
public class AccessForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AccessForbiddenException() {
        super();
    }

    protected AccessForbiddenException(String message, Throwable cause,
                                   boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

}
