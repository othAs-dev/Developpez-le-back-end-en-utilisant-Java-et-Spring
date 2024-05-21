package org.openclassrooms.chatop.message.controller;

import lombok.AllArgsConstructor;
import org.openclassrooms.chatop.message.DTO.MessageDTO;
import org.openclassrooms.chatop.message.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MessageController {

  private MessageService messageService;

  @PostMapping("/messages/{rentalId}")
  public MessageDTO addMessage(@RequestBody MessageDTO messageDTO, @PathVariable UUID rentalId) {
    return messageService.addNewMessage(messageDTO, rentalId);
  }

  @GetMapping("/messages")
  public List<MessageDTO> getMessages() {
    return messageService.getAllMessages();
  }
}
