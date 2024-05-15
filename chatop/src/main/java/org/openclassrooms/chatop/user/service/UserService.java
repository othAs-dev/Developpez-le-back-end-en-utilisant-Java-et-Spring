package org.openclassrooms.chatop.user.service;

import org.openclassrooms.chatop.user.entity.UserDetailEntity;

import java.util.UUID;

public interface UserService {
  UserDetailEntity addNewUser(String username, String password, String email);
  UserDetailEntity loadUserByUsername(String username);

}
