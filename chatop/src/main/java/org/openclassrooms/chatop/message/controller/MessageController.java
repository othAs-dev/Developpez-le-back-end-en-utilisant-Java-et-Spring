package org.openclassrooms.chatop.message.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.openclassrooms.chatop.exceptions.ApiException;
import org.openclassrooms.chatop.message.DTO.MessageDTO;
import org.openclassrooms.chatop.message.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<MessageDTO> addMessage(@RequestBody MessageDTO messageDTO, @PathVariable UUID rentalId) {
    try {
      MessageDTO addedMessage = messageService.addNewMessage(messageDTO, rentalId);
      return ResponseEntity.ok().body(addedMessage);
    } catch (ApiException.NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @Operation(summary = "This method is used to get all messages")
  @GetMapping("/messages")
  public ResponseEntity<List<MessageDTO>> getMessages() {
    try {
      List<MessageDTO> messages = messageService.getAllMessages();
      return ResponseEntity.ok().body(messages);
    } catch (ApiException.NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
