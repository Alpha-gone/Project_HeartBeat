package com.github.heartbeat.rdb;


import com.github.heartbeat.rdb.user.entity.User;
import com.github.heartbeat.rdb.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@Order(1)
@RequiredArgsConstructor
@DisplayName("회원 정보 테스트")
public class UserTest extends HeartbeatRdbTestConfig{
    private final UserRepository repo;

    @Test
    @Order(1)
    @DisplayName("회원 가입")
    void insertUser(){

        List<User> userList = new ArrayList<>();

        userList.add(User.builder()
                    .uuid(UUID.randomUUID())
                    .name("홍길동")
                    .nickName("길동전")
                    .email("test@gmail.com")
                    .password("1q2w3e4r5t")
                    .phoneNumber("0001111222")
                    .build());

        userList.add(User.builder()
                    .uuid(UUID.randomUUID())
                    .name("유관순")
                    .nickName("5만원")
                    .email("test2@gmail.com")
                    .password("1q2w3e4r5t")
                    .phoneNumber("0001111222")
                    .build());

        Assertions.assertNotNull(repo.saveAll(userList), "유저 생성 완료");
    }

    @Test
    @Order(2)
    @DisplayName("회원 목록 조회")
    void findAllUser(){
        List<User> userList = repo.findAll();
        Assertions.assertNotNull(userList);
        Assertions.assertNotEquals(0, userList.size());

        log.info(userList.toString());
    }

    @Test
    @Order(3)
    @DisplayName("회원 탈퇴")
    void deleteUser(){
        log.info(repo.findAll().toString());

        var targetUser = repo.findById(1L).orElseThrow(NoSuchElementException::new);

        Assertions.assertDoesNotThrow(() -> repo.delete(targetUser));
        Assertions.assertDoesNotThrow(() -> repo.deleteById(2L));

        log.info(repo.findAll().toString());
    }
}
