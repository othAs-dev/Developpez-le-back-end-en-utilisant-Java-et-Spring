package org.openclassrooms.chatop.message.service;

import org.openclassrooms.chatop.message.DTO.MessageDTO;

import java.util.List;
import java.util.UUID;

public interface MessageService {

  MessageDTO addNewMessage(MessageDTO messageDTO, UUID rentalId);

  List<MessageDTO> getAllMessages();
}
