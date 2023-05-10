package com.github.heartbeat.rdb.mapping.entity;

import com.github.heartbeat.rdb.group.entity.Group;
import com.github.heartbeat.rdb.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"user","group"})
@Builder
@Entity
@Table(schema = "heartbeat", name = "tb_map_group_user")
public class GroupUserMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_uuid", referencedColumnName = "uuid")
    private Group group;
}
