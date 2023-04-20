package com.github.heartbeat.rdb.apidata.entity;

import com.github.heartbeat.rdb.apidata.constdata.UserStatus;
import com.github.heartbeat.rdb.apidata.converter.StatusConverter;
import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString(exclude = "user")
@Entity
@Table(name = "tb_api_data", schema = "heartbeat")
@DynamicInsert
public class APIData {
    @Id
    @Column(insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

//    @OneToOne(mappedBy = "apiData")
//    private User user;

    @Column(length = 36, nullable = false, updatable = false, unique = true)
    private UUID uuid;

    @Column(nullable = false)
    private LocalDateTime recentDateTime;

    @Column(nullable = false)
    private String recentLocation;

    @Column(nullable = false, columnDefinition = "tinyint", length = 1)
    @ColumnDefault("0")
    private Byte checkCount;

    @Column(columnDefinition = "tinyint", length = 1)
    @Convert(converter = StatusConverter.class)
    private UserStatus status;
}
