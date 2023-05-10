package com.github.heartbeat.rdb.mapping.repo;

import com.github.heartbeat.rdb.mapping.entity.GroupUserMap;
import com.github.heartbeat.rdb.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupUserMapRepository extends JpaRepository<GroupUserMap, Long> {
    List<GroupUserMap> findByUser(User user);
}
