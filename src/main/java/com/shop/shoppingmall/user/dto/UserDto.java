package com.shop.shoppingmall.user.dto;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String password;
    @Nullable
    private String phone;
    @Nullable
    private String auth;
}
