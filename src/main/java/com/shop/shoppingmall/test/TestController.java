package com.shop.shoppingmall.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/")
    public ModelAndView testPage(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/dev/dev");
        return mav;
    }
}
