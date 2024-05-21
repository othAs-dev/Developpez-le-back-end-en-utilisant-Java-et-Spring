package org.openclassrooms.chatop.rentals.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RentalDTO {
  private UUID id;
  private String name;
  private String description;
  private int surface;
  private double price;
  private UUID ownerId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
