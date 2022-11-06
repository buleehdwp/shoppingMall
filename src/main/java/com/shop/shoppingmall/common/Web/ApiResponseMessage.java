package com.shop.shoppingmall.common.Web;


import lombok.Data;

@Data
public class ApiResponseMessage {

    private String message;
    private String status;
    private String errorMessage;
    private String errorCode;

    public ApiResponseMessage() {
    }

    public ApiResponseMessage(String status, String message, String errorCode, String errorMessage) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
