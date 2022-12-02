package com.shop.shoppingmall.reservation.repository;

import com.shop.shoppingmall.reservation.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, String> {

}
