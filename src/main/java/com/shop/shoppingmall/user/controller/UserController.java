package com.shop.shoppingmall.user.controller;

import com.shop.shoppingmall.common.Web.ApiResponseMessage;
import com.shop.shoppingmall.user.dto.UserDto;
import com.shop.shoppingmall.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private UserService userService;


    @PostMapping("/user/submit/signUp")
    public ResponseEntity<ApiResponseMessage> signUpSubmit(@RequestBody UserDto userDto) throws Exception {
        return userService.save(userDto);
    }

    @GetMapping("/user/logout")
    public String logoutSubmit(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }
}
