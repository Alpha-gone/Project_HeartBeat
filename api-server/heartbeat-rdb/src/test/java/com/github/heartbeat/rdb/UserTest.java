package com.github.heartbeat.rdb;


import com.github.heartbeat.rdb.apidata.constdata.UserStatus;
import com.github.heartbeat.rdb.apidata.entity.APIData;
import com.github.heartbeat.rdb.apidata.repo.APIDataRepository;
import com.github.heartbeat.rdb.user.entity.User;
import com.github.heartbeat.rdb.user.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;


@Slf4j
@Order(1)
@RequiredArgsConstructor
@DisplayName("회원 정보 테스트")
public class UserTest extends HeartbeatRdbTestConfig{
    private final UserRepository userRepo;
    private final APIDataRepository apiRepo;

    @Test
    @Order(1)
    @DisplayName("회원 가입")
    void insertUser(){
        var userList = initUserData();

        Assertions.assertNotNull(userRepo.saveAll(userList), "유저 생성 완료");
    }

    @Test
    @Order(2)
    @DisplayName("회원 목록 조회")
    @Transactional
    void findAllUser(){
        List<User> userList = userRepo.findAll();
        List<APIData> apiDataList = apiRepo.findAll();

        Assertions.assertNotNull(userList);
        Assertions.assertNotEquals(0, userList.size());

        Assertions.assertNotNull(apiDataList);
        Assertions.assertNotEquals(0, apiDataList.size());

        userList.forEach(user -> log.info(user.toString()));
        apiDataList.forEach(apiData -> log.info(apiData.toString()));
    }

    @Test
    @Order(3)
    @DisplayName("회원 탈퇴")
    void deleteUser(){
        var targetUser = userRepo.findById(1L).orElseThrow(NoSuchElementException::new);

        Assertions.assertDoesNotThrow(() -> userRepo.delete(targetUser));
        Assertions.assertDoesNotThrow(() -> userRepo.deleteById(2L));

        log.info(userRepo.findAll().toString());
        log.info(apiRepo.findAll().toString());
    }
}
