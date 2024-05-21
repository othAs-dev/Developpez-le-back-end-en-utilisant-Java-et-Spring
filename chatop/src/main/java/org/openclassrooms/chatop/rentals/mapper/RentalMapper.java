package org.openclassrooms.chatop.rentals.mapper;

import org.openclassrooms.chatop.rentals.DTO.RentalDTO;
import org.openclassrooms.chatop.rentals.entity.RentalEntity;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;

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
    rentalDTO.setOwnerId(rentalEntity.getOwner().getId());
    rentalDTO.setCreatedAt(rentalEntity.getCreatedAt());
    rentalDTO.setUpdatedAt(rentalEntity.getUpdatedAt());
    return rentalDTO;
  }

  public static RentalEntity toEntity(RentalDTO rentalDTO, UserDetailEntity owner) {
    if (rentalDTO == null) {
      return null;
    }
    RentalEntity rentalEntity = new RentalEntity();
    rentalEntity.setId(rentalDTO.getId());
    rentalEntity.setName(rentalDTO.getName());
    rentalEntity.setDescription(rentalDTO.getDescription());
    rentalEntity.setSurface(rentalDTO.getSurface());
    rentalEntity.setPrice(rentalDTO.getPrice());
    rentalEntity.setOwner(owner);
    return rentalEntity;
  }
}
