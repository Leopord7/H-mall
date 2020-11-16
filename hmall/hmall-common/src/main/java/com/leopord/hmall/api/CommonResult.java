package com.leopord.hmall.api;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description: 公共返回结果
 * @author: Leopord
 * @time: 2020/11/16 16:06
 */

@Getter
@Setter
public class CommonResult implements Serializable {

    private long code;

    private String message;

    private Object data;

    private CommonResult(StatusCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    private CommonResult(StatusCode responseCode, Object data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
    }

    private CommonResult(long code, String message) {
        this.code = code;
        this.message = message;
    }

    private CommonResult(long code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static CommonResult success(Object data) {
        return new CommonResult(StatusCode.SUCCESS, data);
    }

    public static CommonResult success() {
        return new CommonResult(StatusCode.SUCCESS);
    }

    public static CommonResult error(StatusCode code) {
        return new CommonResult(code, null);
    }

    public static CommonResult error(StatusCode code, String message) {
        return new CommonResult(code.getCode(), message, null);
    }
}
