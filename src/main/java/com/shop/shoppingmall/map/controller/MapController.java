package com.shop.shoppingmall.map.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/map")
public class MapController {

    @GetMapping("/view")
    public ModelAndView getLoadMap(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("page/main/loadMap-V");
        return mav;
    }
}
