package com.shop.shoppingmall.common.security;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@AllArgsConstructor
public class SecurityConfig{

    // 정적 자원 Security 적용 안함
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
    }

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()//post 요청에 csrf 사용하지 않음
                .authorizeRequests()
                .antMatchers("/about").authenticated() //해당 요청은 로그인 혹은 인증 필요
                .antMatchers("/*/admin").hasRole("ADMIN") //해당 요청은 admin 권한 필요
                .anyRequest().permitAll(); //나머지 요청은 모두 허용
//                .and()
//                .formLogin() //로그인 설정
//                .loginPage("/member/loginView") //로그인 페이지
//                .loginProcessingUrl("/loginProc") //로그인 화면에서 form 의 action 주소
//                .usernameParameter("userId")//form 의 input name => default(username)
//                .passwordParameter("userPassword")
//                .successForwardUrl("/") // 로그인 성공시
//                .permitAll();
//                .failureForwardUrl("/member/loginView")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/index");
//                .deleteCookies() //로그아웃 후 쿠키 삭제
        return http.build();
    }
}
