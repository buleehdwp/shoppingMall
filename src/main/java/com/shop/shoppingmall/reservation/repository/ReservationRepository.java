package com.shop.shoppingmall.reservation.repository;

import com.shop.shoppingmall.reservation.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, String> {
}
