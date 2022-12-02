package com.shop.shoppingmall.reservation.entity;

import com.shop.shoppingmall.common.web.Uuid;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="g_reservation")
@Data
public class ReservationEntity extends Uuid {
    @Column(name = "user_uuid")
    private String user;

    @Column(name = "check_in")
    private String checkIn;

    @Column(name = "check_out")
    private String checkOut;

    @Column(name = "created_date", updatable = false)
    private String createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @Column(name = "cancle_date")
    private String cancleDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
}
