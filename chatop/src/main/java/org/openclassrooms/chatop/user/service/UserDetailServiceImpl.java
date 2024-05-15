package org.openclassrooms.chatop.user.service;

import lombok.AllArgsConstructor;
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
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetailEntity user = userService.loadUserByUsername(username);
    if (user == null) throw new UsernameNotFoundException(String.format("User with username %s not found", username));
    return User
      .withUsername(user.getUsername())
      .password(user.getPassword())
      .build();
  }
}
