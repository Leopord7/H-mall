package com.leopord.hmall.api;

import com.leopord.hmall.exception.BaseErrorInterface;
import lombok.Getter;
import lombok.Setter;

public enum StatusCode implements BaseErrorInterface {

    SUCCESS(200, "操作成功"),
    VALIDATE_FAILED(401, "参数检验失败"),
    ERROR(500, "结果错误"),
    ;

    @Getter
    @Setter
    private long code;

    @Getter
    @Setter
    private String message;

    private StatusCode(long code, String message) {
        this.code = code;
        this.message = message;
    }
}
