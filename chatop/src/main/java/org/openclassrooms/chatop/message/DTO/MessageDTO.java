package org.openclassrooms.chatop.message.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class MessageDTO {
  private UUID id;
  private String message;
  private UUID ownerId;
  private UUID rentalId;
}
