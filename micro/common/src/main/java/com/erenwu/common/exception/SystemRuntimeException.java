package com.erenwu.common.exception;


import com.erenwu.common.enums.RespCodeEnum;

/**
 * 系统运行时业务异常
 * @author eren
 */
public class SystemRuntimeException extends RuntimeException {

    private RespCodeEnum respType;

    public SystemRuntimeException(String message) {
        super(message);
    }

    public SystemRuntimeException(String message, Throwable cause) {
        super(message,cause);
    }

    public SystemRuntimeException(String message, RespCodeEnum respType) {
        super(message);
        this.respType = respType;
    }

    public SystemRuntimeException(RespCodeEnum respType) {
        super(respType.getDisplayName());
        this.respType = respType;
    }

    public SystemRuntimeException(String message, Throwable cause, RespCodeEnum respType) {
        super(message, cause);
        this.respType = respType;
    }
    public SystemRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, RespCodeEnum respType) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.respType = respType;
    }

    public RespCodeEnum getRespType() {
        return respType;
    }

    public void setRespType(RespCodeEnum respType) {
        this.respType = respType;
    }
}
