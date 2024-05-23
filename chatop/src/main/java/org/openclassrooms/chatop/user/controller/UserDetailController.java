package org.openclassrooms.chatop.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.openclassrooms.chatop.exceptions.ApiException;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;
import org.openclassrooms.chatop.user.repository.UserDetailRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Tag(name = "User details")
public class UserDetailController {

  private UserDetailRepository userDetailRepository;

  @Operation(summary = "This method is used to get user details by id")
  @GetMapping("/user/{id}")
  public ResponseEntity<UserDetailEntity> getUserDetailById(@PathVariable String id) {
    try {
      Optional<UserDetailEntity> userOptional = userDetailRepository.findById(UUID.fromString(id));
      return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    } catch (IllegalArgumentException e) {
        throw new ApiException.BadRequestException("Failed to get user details by id the error is: " + e.getMessage());
    }
  }
}
