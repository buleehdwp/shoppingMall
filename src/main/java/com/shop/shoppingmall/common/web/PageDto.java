package com.shop.shoppingmall.common.web;

import lombok.Data;
import org.springframework.data.domain.Page;

/**
 * 검색과 페이징을 동시 담당
 * */
@Data
public class PageDto {
    private String keyword;
    private String pageNo;
    //@RequestParam(value = "keyword", required = false) String keyword, @PageableDefault(size = 5, sort = "updateDate", direction = Sort.Direction.DESC) Pageable pageable

    /** 페이징 정보 반환 **/
    public void getPageInfo(Page<?> postPageList, int pageNo) {
        int totalPage = postPageList.getTotalPages();

        // 현재 페이지를 통해 현재 페이지 그룹의 시작 페이지를 구함
        int startNumber = (int)((Math.floor(pageNo/5)*5)+1 <= totalPage ? (Math.floor(pageNo/5)*5)+1 : totalPage);

        // 전체 페이지 수와 현재 페이지 그룹의 시작 페이지를 통해 현재 페이지 그룹의 마지막 페이지를 구함
        int endNumber = (startNumber + 5-1 < totalPage ? startNumber + 5-1 : totalPage);
        boolean hasPrev = postPageList.hasPrevious();
        boolean hasNext = postPageList.hasNext();

        /* 화면에는 원래 페이지 인덱스+1 로 출력됨을 주의 */
        int prevIndex = postPageList.previousOrFirstPageable().getPageNumber()+1;
        int nextIndex = postPageList.nextOrLastPageable().getPageNumber()+1;

//        return new PageVo(totalPage, startNumber, endNumber, hasPrev, hasNext, prevIndex, nextIndex);
    }
}
