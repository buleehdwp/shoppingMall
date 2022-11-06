package com.shop.shoppingmall.notice.service;

import com.shop.shoppingmall.common.Web.ApiResponseMessage;
import com.shop.shoppingmall.notice.Repository.NoticeRepository;
import com.shop.shoppingmall.notice.entity.NoticeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("Notice")
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    public List<NoticeEntity> findAll(){
        return noticeRepository.findAll();
    }

    public ResponseEntity<ApiResponseMessage> noticeInsert(NoticeEntity noticeEntity) {
        try {
            if (noticeEntity.getDelYn() == null) {
                noticeEntity.setDelYn("Y");
            } else {
                noticeEntity.setDelYn("N");
            }
            noticeRepository.save(noticeEntity);
            ApiResponseMessage message = new ApiResponseMessage("Ok", "처리되었습니다.", "", "");
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponseMessage message = new ApiResponseMessage("", "", "error", "오류가 발생했습니다.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
}
