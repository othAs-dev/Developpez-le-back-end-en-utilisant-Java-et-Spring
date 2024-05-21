package org.openclassrooms.chatop.rentals.service;

import org.openclassrooms.chatop.rentals.DTO.RentalDTO;

import java.util.List;
import java.util.UUID;

public interface RentalService {

  RentalDTO addNewRental(RentalDTO rental);


  RentalDTO updateRental(RentalDTO rental, UUID id);

  RentalDTO getRentalById(UUID id);

  List<RentalDTO> getRentals();
}
