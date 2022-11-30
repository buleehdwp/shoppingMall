package com.shop.shoppingmall.notice.controller;

import com.shop.shoppingmall.common.web.ApiResponseMessage;
import com.shop.shoppingmall.common.web.PageDto;
import com.shop.shoppingmall.notice.entity.NoticeEntity;
import com.shop.shoppingmall.notice.service.NoticeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class NoticeController {

    private NoticeService noticeService;

    @PostMapping("/view/notice/search")
    public ResponseEntity<ApiResponseMessage> getSearch(@RequestBody PageDto dto, Pageable pageable) {
        return noticeService.findAll(dto, pageable);
    }

    @PostMapping("/view/notice/getDetail")
    public ResponseEntity<ApiResponseMessage> getDetail(@RequestBody NoticeEntity noticeEntity) throws Exception {
        return noticeService.findTitle(noticeEntity.getTitle());
    }

    /* =============================== Admin =============================== */
    @PostMapping("/admin/submit")
    public ResponseEntity<ApiResponseMessage> noticeInsert(@RequestParam String type, @RequestBody NoticeEntity noticeEntity) {
        return noticeService.noticeInsert(type, noticeEntity);
    }
}
