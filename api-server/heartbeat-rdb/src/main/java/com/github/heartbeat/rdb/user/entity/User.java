package com.github.heartbeat.rdb.user.entity;

import com.github.heartbeat.rdb.apidata.entity.APIData;
import com.github.heartbeat.rdb.group.entity.Group;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    @Column(insertable = false, updatable = false)
    private Long idx;

    @Column(length = 36, nullable = false, updatable = false, unique = true)
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
    private LocalDateTime resistAt;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "api_idx", referencedColumnName = "idx", unique = true, nullable = false)
    private APIData apiData;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "group_user_map",
            joinColumns = @JoinColumn(
                    name = "user_idx",
                    referencedColumnName = "idx"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "group_idx",
                    referencedColumnName = "idx"
            )
    )
    @Column(name = "group_list")
    private List<Group> groupList;

    public void addGroup(Group group){
        if(Objects.isNull(groupList)) groupList = new ArrayList<>();
        groupList.add(group);
    }
}
