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
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class RentalServiceImpl implements RentalService {
  private RentalRepository rentalRepository;
  private UserDetailRepository userDetailRepository;

  @Override
  public RentalDTO addNewRental(RentalDTO rentalDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetailEntity user = userDetailRepository.findByUsername(authentication.getName());
    rentalDTO.setOwnerId(user.getId());

    RentalEntity rentalEntity = RentalMapper.toEntity(rentalDTO, user);
    RentalEntity savedRental = rentalRepository.save(rentalEntity);

    return RentalMapper.toDTO(savedRental);
  }

  @Override
  public RentalDTO updateRental(RentalDTO rentalDTO, UUID id) {
    RentalEntity rental = rentalRepository.findById(id).orElse(null);
    if (rental != null) {
      rental.setName(rentalDTO.getName());
      rental.setDescription(rentalDTO.getDescription());
      rental.setPrice(rentalDTO.getPrice());
      rental.setSurface(rentalDTO.getSurface());
      RentalEntity updatedRental = rentalRepository.save(rental);

      return RentalMapper.toDTO(updatedRental);
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
