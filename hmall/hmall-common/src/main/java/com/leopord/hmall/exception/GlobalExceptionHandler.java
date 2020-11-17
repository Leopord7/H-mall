package com.leopord.hmall.exception;

import com.leopord.hmall.api.CommonResult;
import com.leopord.hmall.api.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description:
 * @author: Leopord
 * @time: 2020/11/17 13:45
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public CommonResult handleBusiness(BusinessException e) {
        if (e.getErrorInterface() == null) {
            return CommonResult.error(StatusCode.ERROR.getCode(), e.getMessage());
        }
        return CommonResult.error((StatusCode) e.getErrorInterface());
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult handleBinding(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuffer message = new StringBuffer();
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                if (message.length() > 0) {
                    message.append(';');
                }
                message.append(fieldError.getField()).append(fieldError.getDefaultMessage());
            }
        }
        return CommonResult.error(StatusCode.VALIDATE_FAILED, message.toString());
    }

    @ResponseBody
    @ExceptionHandler(value = NullPointerException.class)
    public CommonResult handleNullPointer(NullPointerException e) {
        log.error("发生空指针异常，", e);
        return CommonResult.error(StatusCode.ERROR);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult handleUnknownException(Exception e) {
        log.error("发生未知异常，", e);
        return CommonResult.error(StatusCode.ERROR);
    }
}
