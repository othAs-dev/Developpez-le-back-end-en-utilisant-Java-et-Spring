package org.openclassrooms.chatop.rentals.controllers;


import lombok.AllArgsConstructor;
import org.openclassrooms.chatop.rentals.DTO.RentalDTO;
import org.openclassrooms.chatop.rentals.service.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RentalsController {

  private RentalService rentalService;

    @GetMapping("/rentals")
    public List<RentalDTO> getRentals() {
      return rentalService.getRentals();
    }

    @GetMapping("/rental/{id}")
    public RentalDTO getRentalById(@PathVariable UUID id) {
      return rentalService.getRentalById(id);
    }

    @PostMapping("/rentals")
    public RentalDTO addRental(@RequestBody RentalDTO rental) {
      return rentalService.addNewRental(rental);
    }

    @PutMapping("/rentals/{id}")
    public RentalDTO updateRental(@RequestBody RentalDTO rental, @PathVariable UUID id) {
      return rentalService.updateRental(rental, id);
    }
}
