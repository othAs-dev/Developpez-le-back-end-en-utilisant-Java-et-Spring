package org.openclassrooms.chatop.rentals.repository;

import org.openclassrooms.chatop.rentals.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<RentalEntity, Long> {
}
