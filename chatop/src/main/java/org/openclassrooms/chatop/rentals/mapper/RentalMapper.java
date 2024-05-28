package org.openclassrooms.chatop.rentals.mapper;

import org.openclassrooms.chatop.rentals.DTO.RentalDTO;
import org.openclassrooms.chatop.rentals.entity.RentalEntity;

/**
 * Mapper class for converting RentalEntity objects to RentalDTO objects.
 */
public class RentalMapper {

  /**
   * Converts a RentalEntity object to a RentalDTO object.
   *
   * @param rentalEntity The RentalEntity object to convert.
   * @return The converted RentalDTO object.
   */
  public static RentalDTO toDTO(RentalEntity rentalEntity) {
    if (rentalEntity == null) return null;
    return RentalDTO.builder()
      .id(rentalEntity.getId())
      .name(rentalEntity.getName())
      .description(rentalEntity.getDescription())
      .surface(rentalEntity.getSurface())
      .price(rentalEntity.getPrice())
      .picture(rentalEntity.getPicture())
      .owner_id(rentalEntity.getOwner().getId())
      .created_at(rentalEntity.getCreatedAt())
      .updated_at(rentalEntity.getUpdatedAt())
      .build();
  }
}
