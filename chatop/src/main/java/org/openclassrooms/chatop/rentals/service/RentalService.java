package org.openclassrooms.chatop.rentals.service;

import org.openclassrooms.chatop.rentals.DTO.RentalDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface RentalService {

  Map<String, String> addNewRental(String name, int surface, Double price, String description, MultipartFile picture);


  Map<String, String> updateRental(RentalDTO rental, Long id);

  RentalDTO getRentalById(Long id);

  Map<String, List<RentalDTO>> getRentals();
}
