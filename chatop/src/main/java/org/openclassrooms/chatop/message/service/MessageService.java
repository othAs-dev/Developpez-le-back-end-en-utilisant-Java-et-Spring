package org.openclassrooms.chatop.message.service;

import org.openclassrooms.chatop.message.DTO.MessageDTO;

import java.util.List;
import java.util.Map;

public interface MessageService {

  Map<String, String> addNewMessage(MessageDTO messageDTO);

  //List<MessageDTO> getAllMessages();
}
