package com.github.heartbeat.rdb.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;


import java.time.LocalDateTime;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@DynamicInsert
@Table(name = "tb_user", schema = "heartbeat")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 36)
    private UUID uuid;

    @Column(length = 5, nullable = false)
    private String name;

    @Column(length = 10)
    private String nickName;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 12, nullable = false)
    private String password;

    @Column(length = 11,nullable = false, unique = true)
    private String phoneNumber;

    @CreationTimestamp
    private LocalDateTime resistAt;
}
