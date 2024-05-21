package org.openclassrooms.chatop.rentals.repository;

import org.openclassrooms.chatop.rentals.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RentalRepository extends JpaRepository<RentalEntity, UUID> {
}
