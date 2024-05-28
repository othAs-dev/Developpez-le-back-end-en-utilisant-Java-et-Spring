package org.openclassrooms.chatop.user.repository;

import org.openclassrooms.chatop.user.entity.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDetailRepository extends JpaRepository<UserDetailEntity, UUID> {
  UserDetailEntity findByUsername(String username);

  UserDetailEntity findByEmail(String email);


}
