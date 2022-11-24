package com.shop.shoppingmall.common.security;

import com.shop.shoppingmall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService; // 3

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/view/*/*", "/api/user/submit/*").permitAll()
                .antMatchers("/*/*/admin/*").hasRole("ADMIN") //해당 요청은 admin 권한 필요
                .antMatchers("/api/user/*/*").authenticated() //해당 요청은 로그인 인증 필요
                .anyRequest().permitAll() //나머지 요청은 모두 허용
                .and()
                    .formLogin() //로그인 설정
                    .loginPage("/api/login")        //로그인 페이지
                    .usernameParameter("email")     // 아이디 파라미터명 설정
                    .passwordParameter("password")  // 패스워드 파라미터명 설정
                    .successHandler(new loginSuccess()) // 성공 시 수행될 로직
                    .failureHandler(new loginFailure()) //실패 시 수행될 로직
                .and()
                    .logout() // 로그아웃 설정
                    .logoutSuccessUrl("/") // 로그아웃 시 이동할 페이지
                    .invalidateHttpSession(true) // 로그아웃시 세션소멸
                .and()
                    .exceptionHandling().accessDeniedPage("/access-denied");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                // 해당 서비스(userService)에서는 UserDetailsService를 implements해서 loadUserByUsername() 구현해야함 (서비스 참고)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    private class loginSuccess implements AuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            System.out.println("authentication : " + authentication.getPrincipal());
            response.sendRedirect("/"); // 인증이 성공한 후에는 root로 이동
        }
    }

    private class loginFailure implements AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            System.out.println("exception : " + exception.getMessage());
            response.sendRedirect("/api/login?fail"); // 인증이 실패하면 로그인 화면 유지
        }
    }
}
