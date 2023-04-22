package com.github.heartbeat.rdb.group.repo;

import com.github.heartbeat.rdb.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
