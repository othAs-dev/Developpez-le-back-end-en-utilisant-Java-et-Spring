package org.openclassrooms.chatop.message.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MessageDTO {
  private UUID id;
  private String message;
  private UUID user_id;
  private Long rental_id;
}
