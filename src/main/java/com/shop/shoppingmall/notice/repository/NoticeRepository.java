package com.shop.shoppingmall.notice.repository;

import com.shop.shoppingmall.notice.entity.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, String> {

    Page<NoticeEntity> findByDelYnContainingAndTitleContaining(String delYn, String title, Pageable pageable); //페이징

    NoticeEntity findByTitle(String title); //중복확인

    NoticeEntity findByUuid(String uuid); //getEntityByUUID
}
