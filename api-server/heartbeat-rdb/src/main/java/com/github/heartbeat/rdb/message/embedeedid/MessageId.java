package com.github.heartbeat.rdb.message.embedeedid;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Embeddable
public class MessageId implements Serializable {
    private UUID userUuid;
    private UUID messageUuid;
}
