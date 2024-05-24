package org.openclassrooms.chatop.message.repository;

import org.openclassrooms.chatop.message.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}
