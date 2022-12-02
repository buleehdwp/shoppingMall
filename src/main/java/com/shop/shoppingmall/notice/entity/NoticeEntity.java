package com.shop.shoppingmall.notice.entity;

import com.shop.shoppingmall.common.web.Uuid;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "G_BOARD")
@Data
public class NoticeEntity extends Uuid {
    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "del_yn")
    private String delYn;

    @Column(name = "created_date", updatable = false)
    private String createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @Column(name = "update_date")
    private String updateDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
}
