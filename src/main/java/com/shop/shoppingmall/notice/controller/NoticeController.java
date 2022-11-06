package com.shop.shoppingmall.notice.controller;

import com.shop.shoppingmall.common.Web.ApiResponseMessage;
import com.shop.shoppingmall.notice.dto.NoticeDto;
import com.shop.shoppingmall.notice.entity.NoticeEntity;
import com.shop.shoppingmall.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/view")
    public ModelAndView getNotice() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("page/notice/notice-V");
        mav.addObject("list", noticeService.findAll());
        return mav;
    }

    /* =============================== Admin =============================== */
    @GetMapping("/admin/insertView")
    public ModelAndView getNoticeInsertView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("page/notice/notice-I");
        return mav;
    }

    @PostMapping("/admin/insert")
    public ResponseEntity<ApiResponseMessage> noticeInsert(@RequestBody NoticeEntity noticeEntity) {
        return noticeService.noticeInsert(noticeEntity);
    }
}
