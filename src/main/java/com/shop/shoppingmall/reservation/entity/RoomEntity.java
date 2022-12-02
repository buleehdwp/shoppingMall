package com.shop.shoppingmall.reservation.entity;

import com.shop.shoppingmall.common.web.Uuid;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "g_room")
@Data
public class RoomEntity extends Uuid {
    @Column(name = "RUM_NAME")
    private String roomName;

}
