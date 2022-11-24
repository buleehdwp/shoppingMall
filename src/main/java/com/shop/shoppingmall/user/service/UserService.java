package com.shop.shoppingmall.user.service;

import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import com.shop.shoppingmall.common.Web.ApiResponseMessage;
import com.shop.shoppingmall.user.dto.UserDto;
import com.shop.shoppingmall.user.entity.UserEntity;
import com.shop.shoppingmall.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.loader.entity.NaturalIdEntityJoinWalker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     * 회원정보 저장
     *
     * @param userDto 회원정보가 들어있는 DTO
     * @return 저장되는 회원의 PK
     */
    public ResponseEntity<ApiResponseMessage> save(UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        userRepository.save(UserEntity.builder()
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .password(userDto.getPassword()).build()).getUuid();
        ApiResponseMessage message = new ApiResponseMessage(null, "Ok", "회원가입을 축하드립니다.");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * Spring Security 필수 메소드 구현
     *
     * @param email 이메일
     * @return UserDetails
     * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
     */
    @Override // 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 UserEntity.java로 반환 타입 지정 (자동으로 다운 캐스팅됨)
    public UserEntity loadUserByUsername(String email) throws UsernameNotFoundException { // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException((email)));
        return user;
    }
}
