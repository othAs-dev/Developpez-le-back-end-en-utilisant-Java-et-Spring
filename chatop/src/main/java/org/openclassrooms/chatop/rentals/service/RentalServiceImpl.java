package org.openclassrooms.chatop.rentals.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openclassrooms.chatop.rentals.DTO.RentalDTO;
import org.openclassrooms.chatop.rentals.entity.RentalEntity;
import org.openclassrooms.chatop.rentals.mapper.RentalMapper;
import org.openclassrooms.chatop.rentals.repository.RentalRepository;
import org.openclassrooms.chatop.rentals.utils.FileStorage;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;
import org.openclassrooms.chatop.user.repository.UserDetailRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Service class for managing rentals.
 */
@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class RentalServiceImpl implements RentalService {
  private RentalRepository rentalRepository;
  private UserDetailRepository userDetailRepository;
  private FileStorage fileStorage;

  @Override
  public Map<String, String> addNewRental(String name, int surface, Double price, String description, MultipartFile picture) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetailEntity user = userDetailRepository.findByEmail(authentication.getName());
    try {
      String picturePath = fileStorage.imageStorage(picture);
      RentalEntity rentalEntity = RentalEntity.builder()
        .name(name)
        .surface(surface)
        .price(price)
        .description(description)
        .picture(picturePath)
        .owner(user)
        .build();
      rentalRepository.save(rentalEntity);

      log.info("Rental created !");
      return Map.of("message", "Rental created !");
    } catch (IOException e) {
      log.error("Failed to save picture.", e);
      return Map.of("error", "Failed to save picture.");
    }
  }

  /**
   * Updates the details of an existing rental.
   *
   * @param rentalDTO the new details of the rental to update
   * @param id        the ID of the rental to update
   * @return a map containing a success message if the rental is updated successfully, otherwise an error message
   */
  @Override
  public Map<String, String> updateRental(RentalDTO rentalDTO, Long id) {
    try {
      RentalEntity rental = rentalRepository.findById(id).orElse(null);
      if (rental != null) {
        rental.setName(rentalDTO.getName());
        rental.setDescription(rentalDTO.getDescription());
        rental.setPrice(rentalDTO.getPrice());
        rental.setSurface(rentalDTO.getSurface());
        rentalRepository.save(rental);
        log.info("Rental updated !");
        return Map.of("message", "Rental updated !");
      }
    } catch (Exception e) {
      log.error("Failed to update rental.", e);
    }
    return Map.of("error", "Rental not found.");
  }

  /**
   * Get a rental by an id.
   *
   * @param id The id of the rental to get.
   * @return A map containing a message indicating the success of the operation.
   */
  @Override
  public RentalDTO getRentalById(Long id) {
    try {
      RentalEntity rental = rentalRepository.findById(id).orElse(null);
      return RentalMapper.toDTO(rental);
    } catch (Exception e) {
      log.error("Failed to get rental.", e);
      return null;
    }
  }

  /**
   * Get all rentals.
   *
   * @return A map containing a list of all rentals.
   */
  @Override
  public Map<String, List<RentalDTO>> getRentals() {
    try {
      List<RentalEntity> rentals = rentalRepository.findAll();
      List<RentalDTO> rentalDTOs = rentals.stream()
        .map(RentalMapper::toDTO)
        .toList();
      return Map.of("rentals", rentalDTOs);
    } catch (Exception e) {
      log.error("Failed to get rentals.", e);
      return Map.of("error", List.of());
    }
  }
}
