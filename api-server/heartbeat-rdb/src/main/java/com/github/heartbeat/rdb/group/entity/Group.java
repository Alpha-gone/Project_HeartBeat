package com.github.heartbeat.rdb.group.entity;

import com.github.heartbeat.rdb.mapping.entity.GroupUserMap;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tb_group", schema = "heartbeat")
@NamedEntityGraph(name = "groupWithUser", attributeNodes = @NamedAttributeNode(value = "userList" ))
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long idx;

    @Column(nullable = false)
    private UUID uuid;

    @OneToMany(mappedBy = "group")
    @Column(name = "user_list")
    private List<GroupUserMap> userList;
}
