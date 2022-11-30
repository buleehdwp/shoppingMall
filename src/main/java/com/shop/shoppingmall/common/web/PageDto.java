package com.shop.shoppingmall.common.web;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 검색과 페이징을 동시 담당
 */
@Data
public class PageDto {
    private int itemCount = 10; //목록 출력 갯수
    private int pageNo;
    private String keyword;

    public Pageable getPageable(Pageable pageable, PageDto dto) {
        pageNo = dto.getPageNo() - 1;
        pageable = PageRequest.of(this.pageNo, itemCount, Sort.by(Sort.Direction.DESC, "updateDate"));
        return pageable;
    }

    /**
     * 페이징 정보 반환
     **/
    public PageVo getPageInfo(Page<?> page, PageDto dto) {
        int pNo = dto.getPageNo() - 1;
        int totalPage = page.getTotalPages();

        // 현재 페이지를 통해 현재 페이지 그룹의 시작 페이지를 구함
        int startNumber = (int) ((Math.floor(pNo / this.itemCount) * this.itemCount) + 1 <= totalPage ? (Math.floor(pNo / this.itemCount) * this.itemCount) + 1 : totalPage);

        // 전체 페이지 수와 현재 페이지 그룹의 시작 페이지를 통해 현재 페이지 그룹의 마지막 페이지를 구함
        int endNumber = (startNumber + this.itemCount - 1 < totalPage ? startNumber + this.itemCount - 1 : totalPage);

        boolean hasPrev = page.hasPrevious();
        boolean hasNext = page.hasNext();

        /* 화면에는 원래 페이지 인덱스+1 로 출력됨을 주의 */
        int prevIndex = page.previousOrFirstPageable().getPageNumber() + 1;
        int nextIndex = page.nextOrLastPageable().getPageNumber() + 1;

        return new PageVo(page.getContent(), dto.getPageNo(), totalPage, startNumber, endNumber, hasPrev, hasNext, prevIndex, nextIndex);
    }

    @Data
    public class PageVo {
        private List<?> data; // 화면 출력 데이터
        private Map<String, Object> pageInfo = new HashMap<>();

        public PageVo(List<?> data, int pNo, int totalPage, int startNumber, int endNumber, boolean hasPrev, boolean hasNext, int prevIndex, int nextIndex) {
            this.data = data;
            this.pageInfo.put("pNo", pNo); // 총 페이지
            this.pageInfo.put("totalPage", totalPage); // 총 페이지
            this.pageInfo.put("startNumber", startNumber); // 시작페이지
            this.pageInfo.put("endNumber", endNumber); // 마지막 페이지
            this.pageInfo.put("hasPrev", hasPrev); // 이전페이지 존재하는가
            this.pageInfo.put("hasNext", hasNext); // 다음페이지 존재하는가
            this.pageInfo.put("prevIndex", prevIndex);
            this.pageInfo.put("nextIndex", nextIndex);
        }
    }
}
