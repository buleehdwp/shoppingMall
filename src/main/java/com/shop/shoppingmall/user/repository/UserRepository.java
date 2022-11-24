package com.shop.shoppingmall.user.repository;

import com.shop.shoppingmall.notice.entity.NoticeEntity;
import com.shop.shoppingmall.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);
}
