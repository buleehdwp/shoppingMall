package com.shop.shoppingmall.common.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/static/", "classpath:/public/", "classpath:/", "classpath:/resources/", "classpath:/META-INF/resources/"};


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // /에 해당하는 요청은 index로 보내버림
        registry.addViewController("/").setViewName("/page/main");
        // 우선순위 제일 높게 설정
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    // 정적 자원 핸들
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
}
