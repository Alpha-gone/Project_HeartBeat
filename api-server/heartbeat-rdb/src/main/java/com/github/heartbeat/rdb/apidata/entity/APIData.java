package com.github.heartbeat.rdb.apidata.entity;

import com.github.heartbeat.rdb.apidata.constdata.UserStatus;
import com.github.heartbeat.rdb.apidata.converter.StatusConverter;
import com.github.heartbeat.rdb.user.entity.User;
import jakarta.persistence.*;
import lombok.*;



import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "user")
@Data
@Entity
@Table(name = "tb_api_data", schema = "heartbeat")
public class APIData {
    @Id
    @Column(insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @OneToOne(mappedBy = "apiData")
    private User user;

    @Column(nullable = false, updatable = false, unique = true)
    private UUID uuid;

    @Column(nullable = false)
    private LocalDateTime recentDateTime;

    @Column(nullable = false)
    private String recentLocation;

    @Builder.Default
    @Column(nullable = false, columnDefinition = "tinyint", length = 1)
    private Byte checkCount = 0;

    @Column(columnDefinition = "tinyint", length = 1)
    @Convert(converter = StatusConverter.class)
    private UserStatus status;
}
