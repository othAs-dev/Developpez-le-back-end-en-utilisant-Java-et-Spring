package org.openclassrooms.chatop.message.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.openclassrooms.chatop.message.DTO.MessageDTO;
import org.openclassrooms.chatop.message.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Tag(name = "Messages")
public class MessageController {

  private MessageService messageService;

  @Operation(summary = "This method is used to add a new message")
  @PostMapping("/messages/{rentalId}")
  public MessageDTO addMessage(@RequestBody MessageDTO messageDTO, @PathVariable UUID rentalId) {
    return messageService.addNewMessage(messageDTO, rentalId);
  }

  @Operation(summary = "This method is used to get all messages")
  @GetMapping("/messages")
  public List<MessageDTO> getMessages() {
    return messageService.getAllMessages();
  }
}
