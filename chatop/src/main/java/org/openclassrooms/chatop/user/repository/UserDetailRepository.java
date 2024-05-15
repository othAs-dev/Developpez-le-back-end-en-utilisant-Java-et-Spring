package org.openclassrooms.chatop.user.repository;

import org.openclassrooms.chatop.user.entity.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetailEntity, String> {
  UserDetailEntity findByUsername(String username);
}
