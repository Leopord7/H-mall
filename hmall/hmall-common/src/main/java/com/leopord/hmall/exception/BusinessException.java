package com.leopord.hmall.exception;

/**
 * @description: 自定义业务异常
 * @author: Leopord
 * @time: 2020/11/17 11:26
 */
public class BusinessException extends RuntimeException{

    private BaseErrorInterface errorInterface;

    public BusinessException(BaseErrorInterface errorInterface) {
        super(errorInterface.getMessage());
        this.errorInterface = errorInterface;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseErrorInterface getErrorInterface() {
        return errorInterface;
    }
}
