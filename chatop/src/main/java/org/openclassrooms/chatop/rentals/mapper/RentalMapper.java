package org.openclassrooms.chatop.rentals.mapper;

import org.openclassrooms.chatop.rentals.DTO.RentalDTO;
import org.openclassrooms.chatop.rentals.entity.RentalEntity;

public class RentalMapper {

  public static RentalDTO toDTO(RentalEntity rentalEntity) {
    if (rentalEntity == null) {
      return null;
    }
    RentalDTO rentalDTO = new RentalDTO();
    rentalDTO.setId(rentalEntity.getId());
    rentalDTO.setName(rentalEntity.getName());
    rentalDTO.setDescription(rentalEntity.getDescription());
    rentalDTO.setSurface(rentalEntity.getSurface());
    rentalDTO.setPrice(rentalEntity.getPrice());
    rentalDTO.setPicture(rentalEntity.getPicture());
    rentalDTO.setOwner_id(rentalEntity.getOwner().getId());
    rentalDTO.setCreated_at(rentalEntity.getCreatedAt());
    rentalDTO.setUpdated_at(rentalEntity.getUpdatedAt());
    return rentalDTO;
  }
}
