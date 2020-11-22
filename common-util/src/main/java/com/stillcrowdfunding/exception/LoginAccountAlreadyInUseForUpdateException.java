package com.stillcrowdfunding.exception;

/**
 * 保存或更新Admin时如果检测登录账号重复抛出这个异常
 * @author Administrator
 * @date 2020/6/21 18:25
 * @description
 **/
public class LoginAccountAlreadyInUseForUpdateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LoginAccountAlreadyInUseForUpdateException() {
        super();
    }

    protected LoginAccountAlreadyInUseForUpdateException(String message, Throwable cause,
                                                         boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public LoginAccountAlreadyInUseForUpdateException(String message) {
        super(message);
    }

    public LoginAccountAlreadyInUseForUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAccountAlreadyInUseForUpdateException(Throwable cause) {
        super(cause);
    }

}
