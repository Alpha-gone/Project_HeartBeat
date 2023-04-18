package com.github.heartbeat.rdb;


import com.github.heartbeat.rdb.user.repo.UserRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(1)
public class UserTest extends HeartbeatRdbTestConfig{
    private UserRepository repo;

    @Test
    @Order(1)
    void insertUser(){

    }
}
