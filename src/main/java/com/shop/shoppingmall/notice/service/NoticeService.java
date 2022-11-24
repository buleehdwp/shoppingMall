package com.shop.shoppingmall.notice.service;

import com.shop.shoppingmall.common.Web.ApiResponseMessage;
import com.shop.shoppingmall.common.util.Utils;
import com.shop.shoppingmall.notice.Repository.NoticeRepository;
import com.shop.shoppingmall.notice.entity.NoticeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("Notice")
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    //전체보기
    public ResponseEntity<ApiResponseMessage> findAll(String keyword, Pageable pageable) {
        Page<NoticeEntity> entities = noticeRepository.findByDelYnContainingAndTitleContaining("N", keyword, pageable);
        ApiResponseMessage message = new ApiResponseMessage(entities, "Ok", "");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //상세보기
    public ResponseEntity<ApiResponseMessage> findTitle(String keyword) {
        NoticeEntity entity = noticeRepository.findByTitle(keyword);
        ApiResponseMessage message = new ApiResponseMessage(entity, "Ok", "");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //입력, 수정, 삭제
    public ResponseEntity<ApiResponseMessage> noticeInsert(String type, NoticeEntity noticeEntity) {
        String message = null;
        if ("I".equals(type)) {
            //제목 중복확인
            if (!Utils.isNull(noticeRepository.findByTitle(noticeEntity.getTitle()))) {
                return new ResponseEntity<>(new ApiResponseMessage(null, "NE000", "이미 있는 제목입니다."), HttpStatus.BAD_REQUEST);
            }

            try {
                noticeEntity.setDelYn("N"); // 삭제여부
                noticeRepository.save(noticeEntity);
                message = "작성되었습니다";
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(new ApiResponseMessage(null, "NE001", "알 수 없는 에러가 발생했습니다."), HttpStatus.BAD_REQUEST);
            }
        }

        if ("U".equals(type)) {
            try {
                NoticeEntity dbEntity = noticeRepository.findByUuid(noticeEntity.getUuid());
                if (!dbEntity.getTitle().equals(noticeEntity.getTitle())) {
                    if (!Utils.isNull(noticeRepository.findByTitle(noticeEntity.getTitle()))) {
                        return new ResponseEntity<>(new ApiResponseMessage(null, "NE000", "이미 있는 제목입니다."), HttpStatus.BAD_REQUEST);
                    }
                }
                dbEntity.setTitle(noticeEntity.getTitle());
                dbEntity.setCreateBy(noticeEntity.getCreateBy());
                dbEntity.setContent(noticeEntity.getContent());
                dbEntity.setUpdateDate(noticeEntity.getUpdateDate());
                noticeRepository.save(dbEntity);
                message = "수정되었습니다";
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(new ApiResponseMessage(null, "NE002", "알 수 없는 에러가 발생했습니다."), HttpStatus.BAD_REQUEST);
            }
        }

        if ("D".equals(type)) {
            try {
                NoticeEntity dbEntity = noticeRepository.findByUuid(noticeEntity.getUuid());
                dbEntity.setDelYn("Y");
                noticeRepository.save(dbEntity);
                message = "삭제되었습니다.";
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(new ApiResponseMessage(null, "NE003", "알 수 없는 에러가 발생했습니다."), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(new ApiResponseMessage(null, "Ok", message), HttpStatus.OK);
    }
}
