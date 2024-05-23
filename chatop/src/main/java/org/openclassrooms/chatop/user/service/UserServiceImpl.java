package org.openclassrooms.chatop.user.service;

import lombok.AllArgsConstructor;
import org.openclassrooms.chatop.exceptions.ApiException;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;
import org.openclassrooms.chatop.user.repository.UserDetailRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private UserDetailRepository userDetailRepository;
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetailEntity addNewUser(String username, String password, String email) {
    UserDetailEntity userDetailEntity = userDetailRepository.findByUsername(username);
    if (userDetailEntity != null) throw new ApiException.BadRequestException("User already exists");
    if (password.length() < 8) throw new ApiException.BadRequestException("Password must be at least 8 characters long");
    if (password.length() > 32) throw new ApiException.BadRequestException("Password must be at most 32 characters long");
    userDetailEntity = UserDetailEntity.builder()
      .username(username)
      .password(passwordEncoder.encode(password))
      .email(email)
      .build();
      return userDetailRepository.save(userDetailEntity);
  }

  @Override
  public UserDetailEntity loadUserByUsername(String email) {
    return userDetailRepository.findByEmail(email);
  }
}
