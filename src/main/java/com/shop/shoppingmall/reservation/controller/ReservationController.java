package com.shop.shoppingmall.reservation.controller;

import com.shop.shoppingmall.common.web.ApiResponseMessage;
import com.shop.shoppingmall.common.web.PageDto;
import com.shop.shoppingmall.reservation.dto.ReservationDto;
import com.shop.shoppingmall.reservation.entity.ReservationEntity;
import com.shop.shoppingmall.reservation.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ReservationController {

    private ReservationService reservationService;

    @PostMapping("/view/reservation/search")
    public ResponseEntity<ApiResponseMessage> getSearch(@RequestBody ReservationDto dto) {
        return reservationService.findAll(dto);
    }
}
