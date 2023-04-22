package com.github.heartbeat.rdb;

import com.github.heartbeat.rdb.group.entity.Group;
import com.github.heartbeat.rdb.group.repo.GroupRepository;
import com.github.heartbeat.rdb.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Order(2)
@RequiredArgsConstructor
@DisplayName("그룹 정보 테스트")
public class GroupTest extends HeartbeatRdbTestConfig{
    private final GroupRepository groupRepo;
    private final UserRepository userRepo;

    @Test
    @Order(1)
    @DisplayName("그룹 생성")
    void generateGroup(){
       List<Group> groupList = new ArrayList<>();
       groupList.add(
               Group.builder()

                       .build());
    }
}
