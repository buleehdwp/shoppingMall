package com.shop.shoppingmall.common.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/static/", "classpath:/public/", "classpath:/", "classpath:/resources/", "classpath:/META-INF/resources/"};


/*    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "OPTIONS")
                .allowedHeaders("headers")
                .maxAge(3000);
    }*/

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/page/main"); // /에 해당하는 요청은 index로 보내버림
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE); // 우선순위 제일 높게 설정
        //{권한없는 페이지 접근}
        registry.addViewController("/access-denied").setViewName("page/user/denied");//{유저_로그인}

        //{유저}
        registry.addViewController("/api/login").setViewName("page/user/login");//{유저_로그인}
        registry.addViewController("/api/view/user/signUp").setViewName("page/user/signUp");//{유저_회원가입}
        registry.addViewController("/api/user/view/myPage").setViewName("page/user/myPage");//{유저_마이페이지}
        registry.addViewController("/api/user/admin/page").setViewName("page/user/admin");//{유저_관리자_페이지}


        //{공지사항}
        registry.addViewController("/api/view/notice/listPage").setViewName("page/notice/notice_view");//{공지사항_리스트}
        registry.addViewController("/api/view/notice/detailPage").setViewName("page/notice/notice_detail");//{공지사항_상세보기}
        registry.addViewController("/api/user/admin/insert").setViewName("page/notice/notice_insert");//{공지사항_수정하기}
    }

    // 정적 자원 핸들
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
}
