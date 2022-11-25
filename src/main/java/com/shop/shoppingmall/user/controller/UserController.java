package com.shop.shoppingmall.user.controller;

import com.shop.shoppingmall.common.web.ApiResponseMessage;
import com.shop.shoppingmall.user.dto.UserDto;
import com.shop.shoppingmall.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    // 로그아웃
    @GetMapping("/user/logout")
    public void logoutSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        response.sendRedirect("/");
    }

    // 회원가입
    @PostMapping("/send/user/signUp")
    public ResponseEntity<ApiResponseMessage> signUpSubmit(@RequestBody UserDto userDto) throws Exception {
        return userService.save(userDto);
    }


}
