package com.github.heartbeat.rdb.message.repo;

import com.github.heartbeat.rdb.message.embedeedid.MessageId;
import com.github.heartbeat.rdb.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, MessageId> {
}
