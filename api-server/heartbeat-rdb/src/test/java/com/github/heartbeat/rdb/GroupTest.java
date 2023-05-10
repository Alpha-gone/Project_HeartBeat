package com.github.heartbeat.rdb;

import com.github.heartbeat.rdb.apidata.constdata.UserStatus;
import com.github.heartbeat.rdb.apidata.entity.APIData;
import com.github.heartbeat.rdb.group.entity.Group;
import com.github.heartbeat.rdb.group.repo.GroupRepository;
import com.github.heartbeat.rdb.mapping.entity.GroupUserMap;
import com.github.heartbeat.rdb.mapping.repo.GroupUserMapRepository;
import com.github.heartbeat.rdb.user.entity.User;
import com.github.heartbeat.rdb.user.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.slf4j.Marker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@Order(2)
@RequiredArgsConstructor
@DisplayName("그룹 정보 테스트")
public class GroupTest extends HeartbeatRdbTestConfig{
    private final GroupRepository groupRepo;
    private final UserRepository userRepo;
    private final GroupUserMapRepository groupMapRepo;
    private List<User> userList = initUserData();

    @Test
    @Order(1)
    @DisplayName("그룹, 매핑 생성")
    void createGroupMapping(){
        var group = Group.builder().uuid(UUID.randomUUID()).build();
        var mapList = new ArrayList<GroupUserMap>();

        for (int i = 0; i < 10; i++){
            mapList.add(
                    GroupUserMap.builder().group(group).user(userList.get(i % 2)).build()
            );
        }

        userRepo.saveAll(userList);
        Assertions.assertNotNull(groupRepo.save(group));
        Assertions.assertNotNull(groupMapRepo.saveAll(mapList));
    }

    @Transactional
    @Test
    @Order(2)
    @DisplayName("생성 결과 확인")
    void getList(){
        var groupFindList = groupRepo.findAll();
        var userFindList = userRepo.findAll();
        var groupMapFindList = groupMapRepo.findAll();

        Assertions.assertNotNull(groupFindList);
        Assertions.assertNotNull(userFindList);
        Assertions.assertNotNull(groupMapFindList);
        Assertions.assertNotEquals(0,groupFindList.size());
        Assertions.assertNotEquals(0, userFindList.size());
        Assertions.assertNotEquals(0, groupMapFindList.size());

        groupFindList.forEach(group -> log.info(group.toString()));
        userFindList.forEach(user -> log.info(user.toString()));
        groupMapFindList.forEach(map -> log.info(map.toString()));
    }

    @Test
    @Order(3)
    @DisplayName("그룹 유저 추가")
    void joinUser(){
        var apidata = APIData.builder()
                .uuid(UUID.randomUUID())
                .recentDateTime(LocalDateTime.now())
                .recentLocation("TEST|Test")
                .status(UserStatus.GOOD)
                .build();

        var user = User.builder()
                .uuid(UUID.randomUUID())
                .name("그룹")
                .email("test3@gmail.com")
                .password("qwert1234")
                .phoneNumber("44455556666")
                .apiData(apidata)
                .build();

        var group = groupRepo.findById(1L).orElseThrow(() -> new NoSuchElementException());

        var groupJoinUser = GroupUserMap.builder().group(group).user(user).build();

        userRepo.save(user);
        groupMapRepo.save(groupJoinUser);
    }

    @Transactional
    @Test
    @Order(4)
    @DisplayName("유저 추가 확인")
    void getUserJoinResult(){
        var userResult = userRepo.findById(3L).orElseThrow(() -> new NoSuchElementException());
        var groupResult = groupRepo.findById(1L).orElseThrow(() -> new NoSuchElementException());
        var groupJoinResult = groupMapRepo.findByUser(userResult);

        Assertions.assertNotNull(userResult);
        Assertions.assertNotNull(groupResult);
        Assertions.assertNotNull(groupJoinResult);
        Assertions.assertNotEquals(0, groupJoinResult.size());

        log.info(userResult.toString());
        log.info(groupResult.toString());
        groupJoinResult.forEach(result -> log.info(result.toString()));
    }
}
