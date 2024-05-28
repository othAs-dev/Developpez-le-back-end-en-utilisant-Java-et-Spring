package org.openclassrooms.chatop.rentals.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.openclassrooms.chatop.rentals.DTO.RentalDTO;
import org.openclassrooms.chatop.rentals.service.RentalService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Tag(name = "Rentals")
public class RentalsController {

  private RentalService rentalService;

  @Operation(summary = "This method is used to get all rentals")
  @GetMapping("/rentals")
  public Map<String, List<RentalDTO>> getRentals() {
    return rentalService.getRentals();
  }

  @Operation(summary = "This method is used to get rental by id")
  @GetMapping("/rentals/{id}")
  public RentalDTO getRentalById(@PathVariable Long id) {
    return rentalService.getRentalById(id);
  }

  @Operation(summary = "This method is used to add a new rental")
  @PostMapping(value = "/rentals")
  public Map<String, String> addRental(@RequestParam("name") String name,
                                       @RequestParam("surface") int surface,
                                       @RequestParam("price") Double price,
                                       @RequestPart("picture") MultipartFile picture,
                                       @RequestParam("description") String description) {
    return rentalService.addNewRental(name, surface, price, description, picture);
  }

  @Operation(summary = "This method is used to delete a rental")
  @PutMapping("/rentals/{id}")
  public Map<String, String> updateRental(@RequestParam("name") String name,
                                          @RequestParam("surface") int surface,
                                          @RequestParam("price") Double price,
                                          @RequestParam("description") String description,
                                          @PathVariable Long id) {
    RentalDTO rentalDTO = RentalDTO.builder()
      .name(name)
      .surface(surface)
      .price(price)
      .description(description)
      .build();

    return rentalService.updateRental(rentalDTO, id);
  }
}
