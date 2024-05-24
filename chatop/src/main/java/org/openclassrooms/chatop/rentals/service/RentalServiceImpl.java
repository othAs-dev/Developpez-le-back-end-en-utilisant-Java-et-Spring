package org.openclassrooms.chatop.rentals.service;

import lombok.AllArgsConstructor;
import org.openclassrooms.chatop.rentals.DTO.RentalDTO;
import org.openclassrooms.chatop.rentals.entity.RentalEntity;
import org.openclassrooms.chatop.rentals.mapper.RentalMapper;
import org.openclassrooms.chatop.rentals.repository.RentalRepository;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;
import org.openclassrooms.chatop.user.repository.UserDetailRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class RentalServiceImpl implements RentalService {
  private RentalRepository rentalRepository;
  private UserDetailRepository userDetailRepository;

  @Override
  public Map<String, String> addNewRental(RentalDTO rentalDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetailEntity user = userDetailRepository.findByEmail(authentication.getName());
    rentalDTO.setOwner_id(user.getId());

    RentalEntity rentalEntity = RentalMapper.toEntity(rentalDTO, user);
    rentalRepository.save(rentalEntity);

    return Map.of("message", "Rental created !");
  }

  @Override
  public Map<String, String> updateRental(RentalDTO rentalDTO, UUID id) {
    RentalEntity rental = rentalRepository.findById(id).orElse(null);
    if (rental != null) {
      rental.setName(rentalDTO.getName());
      rental.setDescription(rentalDTO.getDescription());
      rental.setPrice(rentalDTO.getPrice());
      rental.setSurface(rentalDTO.getSurface());
      rentalRepository.save(rental);

      return Map.of("message", "Rental updated !");
    }
    return null;
  }

  @Override
  public RentalDTO getRentalById(UUID id) {
    RentalEntity rental = rentalRepository.findById(id).orElse(null);
    return RentalMapper.toDTO(rental);
  }

  @Override
  public List<RentalDTO> getRentals() {
    List<RentalEntity> rentals = rentalRepository.findAll();
    return rentals.stream()
      .map(RentalMapper::toDTO)
      .collect(Collectors.toList());
  }
}
