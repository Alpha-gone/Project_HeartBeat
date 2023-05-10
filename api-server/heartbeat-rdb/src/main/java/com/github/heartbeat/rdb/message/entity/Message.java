package com.github.heartbeat.rdb.message.entity;

import com.github.heartbeat.rdb.message.embedeedid.MessageId;
import com.github.heartbeat.rdb.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tb_message", schema = "heartbeat")
public class Message {
    @EmbeddedId
    private MessageId messageId;

    @MapsId("userUuid")
    @ManyToOne
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid")
    private User user;

    private String contacts;

    private String title;

    private String  mainText;
}
