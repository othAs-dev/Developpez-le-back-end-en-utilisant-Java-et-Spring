package org.openclassrooms.chatop.message.repository;

import org.openclassrooms.chatop.message.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {
}
