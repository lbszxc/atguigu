package com.stillcrowdfunding.exception;

/**
 * 保存或更新Admin时如果检测登录账号重复抛出这个异常
 * @author Administrator
 * @date 2020/6/21 18:25
 * @description
 **/
public class LoginAccountAlreadyInUseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LoginAccountAlreadyInUseException() {
        super();
    }

    protected LoginAccountAlreadyInUseException(String message, Throwable cause,
                                       boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public LoginAccountAlreadyInUseException(String message) {
        super(message);
    }

    public LoginAccountAlreadyInUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAccountAlreadyInUseException(Throwable cause) {
        super(cause);
    }

}
