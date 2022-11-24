package com.shop.shoppingmall.common.Web;


import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import lombok.Data;

@Data
public class ApiResponseMessage {

    private Object data;
    private String message;
    private String status;

    public ApiResponseMessage() {
    }

    public ApiResponseMessage(@Nullable Object data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }
}
