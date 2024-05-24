package org.openclassrooms.chatop.rentals.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.openclassrooms.chatop.rentals.DTO.RentalDTO;
import org.openclassrooms.chatop.rentals.service.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Tag(name = "Rentals")
public class RentalsController {

  private RentalService rentalService;

    @Operation(summary = "This method is used to get all rentals")
    @GetMapping("/rentals")
    public List<RentalDTO> getRentals() {
      return rentalService.getRentals();
    }

    @Operation(summary = "This method is used to get rental by id")
    @GetMapping("/rental/{id}")
    public RentalDTO getRentalById(@PathVariable UUID id) {
      return rentalService.getRentalById(id);
    }

    @Operation(summary = "This method is used to add a new rental")
    @PostMapping("/rentals")
    public Map<String, String> addRental(@RequestBody RentalDTO rental) {
      return rentalService.addNewRental(rental);
    }

    @Operation(summary = "This method is used to delete a rental")
    @PutMapping("/rentals/{id}")
    public Map<String, String> updateRental(@RequestBody RentalDTO rental, @PathVariable UUID id) {
      return rentalService.updateRental(rental, id);
    }
}
