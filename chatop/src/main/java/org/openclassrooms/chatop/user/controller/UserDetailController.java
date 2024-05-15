package org.openclassrooms.chatop.user.controller;

import lombok.AllArgsConstructor;
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
public class UserDetailController {

  private UserDetailRepository userDetailRepository;

  @GetMapping("/user/{id}")
  public ResponseEntity<UserDetailEntity> getUserDetailById(@PathVariable String id) {
    Optional<UserDetailEntity> userOptional = userDetailRepository.findById(UUID.fromString(id));
      return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
}
