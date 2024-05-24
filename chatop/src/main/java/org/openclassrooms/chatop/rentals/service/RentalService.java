package org.openclassrooms.chatop.rentals.service;

import org.openclassrooms.chatop.rentals.DTO.RentalDTO;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface RentalService {

  Map<String, String> addNewRental(RentalDTO rental);


  Map<String, String> updateRental(RentalDTO rental, UUID id);

  RentalDTO getRentalById(UUID id);

  List<RentalDTO> getRentals();
}
