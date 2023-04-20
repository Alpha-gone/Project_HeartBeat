package com.github.heartbeat.rdb.apidata.repo;

import com.github.heartbeat.rdb.apidata.entity.APIData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APIDataRepository extends JpaRepository<APIData, Long> {
}
