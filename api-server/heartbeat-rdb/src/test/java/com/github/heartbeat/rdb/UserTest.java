package com.github.heartbeat.rdb;


import com.github.heartbeat.rdb.apidata.entity.APIData;
import com.github.heartbeat.rdb.apidata.repo.APIDataRepository;
import com.github.heartbeat.rdb.group.repo.GroupRepository;
import com.github.heartbeat.rdb.user.entity.User;
import com.github.heartbeat.rdb.user.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.*;


@Slf4j
@Order(1)
@RequiredArgsConstructor
@DisplayName("회원 정보 테스트")
public class UserTest extends HeartbeatRdbTestConfig{
    private final UserRepository userRepo;
    private final APIDataRepository apiRepo;

    @Nested
    @Order(1)
    @DisplayName("회원 가입")
    class InsertUser{
        @Test
        @DisplayName("성공")
        void success(){
            var userList = initUserData();

            var result = userRepo.saveAll(userList);

            Assertions.assertNotNull(result, "유저 생성 결과");
            Assertions.assertEquals(userList.size(), result.size());
        }

        @Test
        @DisplayName("실패")
        void fail(){
            var user = User.builder()
                    .uuid(UUID.randomUUID())
                    .name("TestFail")
                    .email("failtest@gmail.com")
                    .password("1111888899999")
                    .phoneNumber("010101001")
                    .apiData(null)
                    .build();

            var exceptionMessage = Assertions.assertThrows(Exception.class, () -> userRepo.save(user));
            log.error(exceptionMessage.getLocalizedMessage());
        }
    }

    @Nested
    @Order(2)
    @DisplayName("회원 목록")
    class FindUser{
        @Test
        @DisplayName("전체 조회")
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
        @Transactional
        @DisplayName("UUID로 조회")
        void findByUuid(){
            var userList = userRepo.findAll();

            var result = Assertions.assertDoesNotThrow(() -> userRepo.findByUuid(userList.get(0).getUuid()));

            log.info(result.toString());
        }
    }

    @Nested
    @Order(3)
    @DisplayName("회원 탈퇴")
    class DeleteUser{
        @Test
        @Transactional
        @Order(1)
        @DisplayName("엔티티 지정 제거")
        void inputEntity(){
            var targetUser = userRepo.findById(1L).orElseThrow(NoSuchElementException::new);

            Assertions.assertDoesNotThrow(() -> userRepo.delete(targetUser));

            showTable();
        }

        @Test
        @Transactional
        @Order(2)
        @DisplayName("UUID 지정")
        void inputId(){
            var user = userRepo.findAll().get(1);

            Assertions.assertDoesNotThrow(() -> userRepo.deleteByUuid(user.getUuid()));

            showTable();
        }

        private void showTable(){
            log.info(userRepo.findAll().toString());
            log.info(apiRepo.findAll().toString());
        }
    }

}
