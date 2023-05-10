package com.github.heartbeat.rdb.group.repo;

import com.github.heartbeat.rdb.group.entity.Group;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @EntityGraph(value = "groupWithUser")
    List<Group> findAll();
}
