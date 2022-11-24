//package com.shop.shoppingmall.common.security;
//
//import com.shop.shoppingmall.user.service.UserService;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
//import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@ConditionalOnDefaultWebSecurity
//@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
//@RequiredArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//
//    private final UserService userService; // 3
//
//
//    // 정적 자원 Security 적용 안함
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
//    }
//
//    @Bean
//    @Order(SecurityProperties.BASIC_AUTH_ORDER)
//    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()//post 요청에 csrf 사용하지 않음
//                .authorizeRequests()
//                .antMatchers("/user/authentication").permitAll()
//                .antMatchers("/user/*").authenticated() //해당 요청은 로그인 혹은 인증 필요
//                .antMatchers("/admin/*").hasRole("GORILLA") //해당 요청은 admin 권한 필요
//                .anyRequest().permitAll() //나머지 요청은 모두 허용
//                .and()
//                .formLogin() //로그인 설정
//                .loginPage("/user/authentication") //로그인 페이지
//                .successForwardUrl("/")
//                .and().addFilterBefore(); // 로그인 성공시
//        return http.build();
//
////        http.csrf().disable()//post 요청에 csrf 사용하지 않음
////                .authorizeRequests()
////                .antMatchers("/user/authentication").permitAll()
////                .antMatchers("/user/*").authenticated() //해당 요청은 로그인 혹은 인증 필요
////                .antMatchers("/admin/*").hasRole("GORILLA") //해당 요청은 admin 권한 필요
////                .anyRequest().permitAll() //나머지 요청은 모두 허용
////                .and()
////                .formLogin() //로그인 설정
////                .loginPage("/user/authentication") //로그인 페이지
//////                .loginProcessingUrl("/loginProc") //로그인 화면에서 form 의 action 주소
//////                .usernameParameter("userId")//form 의 input name => default(username)
//////                .passwordParameter("userPassword")
////                .successForwardUrl("/"); // 로그인 성공시
//////                .permitAll();
//////                .failureForwardUrl("/member/loginView")
//////                .and()
//////                .logout()
//////                .logoutUrl("/logout")
//////                .logoutSuccessUrl("/index");
//////                .deleteCookies() //로그아웃 후 쿠키 삭제
//////        .and().addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
////        return http.build();
//    }
//
//    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 9
//        auth.userDetailsService(userService)
//                // 해당 서비스(userService)에서는 UserDetailsService를 implements해서
//                // loadUserByUsername() 구현해야함 (서비스 참고)
//                .passwordEncoder(new BCryptPasswordEncoder());
//    }
//}
