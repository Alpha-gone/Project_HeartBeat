package com.github.heartbeat.rdb;


import com.github.heartbeat.rdb.apidata.constdata.UserStatus;
import com.github.heartbeat.rdb.apidata.entity.APIData;
import com.github.heartbeat.rdb.user.entity.User;
import org.junit.jupiter.api.*;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EnableAutoConfiguration
@ComponentScan
@Disabled
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Testcontainers
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.show-sql=true",
        "spring.jooq.sql-dialect=org.hibernate.dialect.MySQL8Dialect"})
@SpringBootTest(classes = HeartbeatRdbTestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
abstract class HeartbeatRdbTestConfig {
    @Container
    private static final MySQLContainer MY_SQL_CONTAINER = new MySQLContainer("mysql:latest");

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(MY_SQL_CONTAINER::stop));
    }

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
    }

    List<User> initUserData(){
        List<APIData> apiDataList = new ArrayList<>();
        apiDataList.add(
                APIData.builder()
                        .uuid(UUID.randomUUID())
                        .recentDateTime(LocalDateTime.now())
                        .recentLocation("위도,경도")
                        .checkCount((byte) 0)
                        .status(UserStatus.GOOD)
                        .build()
        );

        apiDataList.add(
                APIData.builder()
                        .uuid(UUID.randomUUID())
                        .recentDateTime(LocalDateTime.now())
                        .recentLocation("위도,경도")
                        .checkCount((byte) 0)
                        .status(UserStatus.GOOD)
                        .build()
        );

        List<User> userList = new ArrayList<>();
        userList.add(
                User.builder()
                        .uuid(UUID.randomUUID())
                        .name("홍길동")
                        .nickName("길동전")
                        .email("test@gmail.com")
                        .password("1q2w3e4r5t")
                        .phoneNumber("0001111222")
                        .apiData(apiDataList.get(0))
                        .build());

        userList.add(
                User.builder()
                        .uuid(UUID.randomUUID())
                        .name("유관순")
                        .nickName("5만원")
                        .email("test2@gmail.com")
                        .password("1q2w3e4r5t")
                        .phoneNumber("33344445555")
                        .apiData(apiDataList.get(1))
                        .build());

        return userList;
    }
}
