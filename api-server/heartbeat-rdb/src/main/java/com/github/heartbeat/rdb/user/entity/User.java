package com.github.heartbeat.rdb.user.entity;

import com.github.heartbeat.rdb.apidata.entity.APIData;
import com.github.heartbeat.rdb.mapping.entity.GroupUserMap;
import com.github.heartbeat.rdb.message.entity.Message;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tb_user", schema = "heartbeat")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long idx;

    @Column(nullable = false, updatable = false, unique = true)
    private UUID uuid;

    @Column(length = 5, nullable = false, updatable = false)
    private String name;

    @Column(length = 10)
    private String nickName;

    @Column(length = 50, nullable = false, unique = true, updatable = false)
    private String email;

    @Column(length = 12, nullable = false)
    private String password;

    @Column(length = 11,nullable = false, unique = true)
    private String phoneNumber;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime resistAt;

    @OneToMany(mappedBy = "user")
    private List<Message> messages;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "api_idx", referencedColumnName = "idx", unique = true, nullable = false)
    private APIData apiData;

    @OneToMany(mappedBy = "user")
    @Column(name = "group_list")
    private List<GroupUserMap> groupList;
}
