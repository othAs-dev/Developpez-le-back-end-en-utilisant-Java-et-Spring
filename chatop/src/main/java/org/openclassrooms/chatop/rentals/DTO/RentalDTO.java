package org.openclassrooms.chatop.rentals.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RentalDTO {
  private Long id;
  private String name;
  private String description;
  private int surface;
  private double price;
  private String picture;
  private UUID owner_id;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;
}
