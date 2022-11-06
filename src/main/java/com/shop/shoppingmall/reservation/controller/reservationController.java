package com.shop.shoppingmall.reservation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/reservation")
public class reservationController {

    @GetMapping("/view")
    public ModelAndView getReservation() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("page/reservation/reservation-V");
        return mav;
    }
}
