package com.erenwu.common.handler;


import com.erenwu.common.enums.RespCodeEnum;
import com.erenwu.common.exception.NotPermissionException;
import com.erenwu.common.exception.NotRoleException;
import com.erenwu.common.exception.SystemRuntimeException;
import com.erenwu.common.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author eren
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = SystemRuntimeException.class)
    public R<Void> bizExceptionHandler(SystemRuntimeException e) {
        log.warn("当前调用出现异常!",e);
        return R.buildNoObj(e.getRespType() != null ? e.getRespType() : RespCodeEnum.E0500, e.getMessage());
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = NotPermissionException.class)
    public R<Void> perExceptionHandler(NotPermissionException e) {
        log.warn("当前调用出现异常!",e);
        return R.buildNoObj(RespCodeEnum.A0301, e.getMessage());
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = NotRoleException.class)
    public R<Void> roleExceptionHandler(NotRoleException e) {
        log.warn("当前调用出现异常!",e);
        return R.buildNoObj(RespCodeEnum.A0301, e.getMessage());
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value = Exception.class)
    public R<Void> exceptionHandler(Exception e) {
        log.warn("当前调用出现异常!",e);
        return R.buildNoObj(RespCodeEnum.E0500, "系统错误");
    }
}
