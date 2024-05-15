package org.openclassrooms.chatop.rentals.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RentalsController {

    @GetMapping("/rentals")
    public String getRentals() {
        return "Rentals";
    }
}
