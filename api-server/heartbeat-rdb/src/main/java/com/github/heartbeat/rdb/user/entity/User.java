package com.github.heartbeat.rdb.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tb_user", schema = "heartbeat",
        uniqueConstraints = @UniqueConstraint(name = "eamil_uniqued", columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private transient Long idx;

    @Column(columnDefinition = "varchar(5)", nullable = false)
    private String name;

    @Column(columnDefinition = "varchar(10)")
    private String nickName;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String email;

    @Column(columnDefinition = "varchar(12)", nullable = false)
    private String password;

    @Column(columnDefinition = "varchar(11)", nullable = false)
    private String phoneNumber;

    @ColumnDefault("current_timestamp")
    private LocalDateTime resistAt;

}
