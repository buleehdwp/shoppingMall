package com.shop.shoppingmall.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * global exception handler
 * 추가적으로 발생하는 커스텀 처리가 필요한 에러들은 여기서 처리
 * */

@ControllerAdvice
public class GlobalCatcher {

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED) // requestMapping method 불일치 에러
    @ExceptionHandler(Exception.class)
    public String cather(Exception e, Model model) {
        model.addAttribute("ex", e);
        return "common/error/exception";

    }
}
