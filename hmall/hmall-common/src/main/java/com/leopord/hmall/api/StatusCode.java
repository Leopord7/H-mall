package com.leopord.hmall.api;

import lombok.Getter;
import lombok.Setter;

public enum StatusCode {

    SUCCESS(200, "操作成功"),
    ERROR(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
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
