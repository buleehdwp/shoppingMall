package com.shop.shoppingmall.reservation.service;

import com.shop.shoppingmall.common.web.ApiResponseMessage;
import com.shop.shoppingmall.reservation.dto.ReservationDto;
import com.shop.shoppingmall.reservation.entity.RoomEntity;
import com.shop.shoppingmall.reservation.repository.ReservationRepository;
import com.shop.shoppingmall.reservation.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {
    private ReservationRepository reservationRepository;
    private RoomRepository roomRepository;

    public ResponseEntity<ApiResponseMessage> findAll(ReservationDto dto) {
        List<RoomEntity> roomEntity = roomRepository.findAll();
        ApiResponseMessage message = new ApiResponseMessage(roomEntity, "Ok", "");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
