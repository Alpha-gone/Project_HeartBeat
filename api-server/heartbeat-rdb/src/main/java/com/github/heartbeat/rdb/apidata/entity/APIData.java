package com.github.heartbeat.rdb.apidata.entity;

import com.github.heartbeat.rdb.apidata.constdata.UserStatus;
import com.github.heartbeat.rdb.apidata.converter.StatusConverter;
import com.github.heartbeat.rdb.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class APIData {
    @Id
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid")
    private User user;

    @Column(nullable = false)
    private LocalDateTime recentDateTime;

    @Column(nullable = false)
    private String recentLocation;

    @Column(nullable = false, columnDefinition = "tinyint(1)")
    @ColumnDefault("0")
    private Short checkCount;

    @Column(columnDefinition = "tinyint(1)")
    @Convert(converter = StatusConverter.class)
    private UserStatus status;
}
