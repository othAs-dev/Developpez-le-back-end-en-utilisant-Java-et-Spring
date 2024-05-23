package org.openclassrooms.chatop.user.service;

import lombok.AllArgsConstructor;
import org.openclassrooms.chatop.exceptions.ApiException;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {

  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserDetailEntity user = userService.loadUserByUsername(email);
    if (user == null) throw new ApiException.NotFoundException(String.format("User with email %s not found", email));
    return User
      .withUsername(user.getEmail())
      .password(user.getPassword())
      .build();
  }
}
