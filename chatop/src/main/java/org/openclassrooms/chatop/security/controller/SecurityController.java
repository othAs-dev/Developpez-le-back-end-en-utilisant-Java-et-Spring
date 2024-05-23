package org.openclassrooms.chatop.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.openclassrooms.chatop.security.DTO.LoginDTO;
import org.openclassrooms.chatop.security.DTO.RegisterDTO;
import org.openclassrooms.chatop.security.DTO.UserDetailDTO;
import org.openclassrooms.chatop.security.mapper.UserDetailMapper;
import org.openclassrooms.chatop.security.utils.generators.GenerateToken;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;
import org.openclassrooms.chatop.user.repository.UserDetailRepository;
import org.openclassrooms.chatop.user.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Tag(name = "Security")
public class SecurityController {

  private final AuthenticationManager authenticationManager;
  private final UserDetailRepository userDetailRepository;
  private final UserService userService;
  private final GenerateToken generateToken;

  @Operation(summary = "This method is used to login")
  @PostMapping("/login")
  public Map<String, String> login(@RequestBody LoginDTO loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
    );
    String jwt = generateToken.generateTokenFunction(authentication);
    return Map.of("token", jwt);
  }

  @Operation(summary = "This method is used to register")
  @PostMapping("/register")
  public Map<String, String> register(@RequestBody RegisterDTO registerRequest) {
    userService.addNewUser(
      registerRequest.getName(),
      registerRequest.getPassword(),
      registerRequest.getEmail()
    );

    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword())
    );
    String jwt = generateToken.generateTokenFunction(authentication);
    return Map.of("token", jwt);
  }

  @Operation(summary = "This method is used to get user details who is logged in")
  @GetMapping("/me")
  public UserDetailDTO getUserDetails(Authentication authentication) {
    String email = authentication.getName();
    UserDetailEntity userEntity = userDetailRepository.findByEmail(email);
    return UserDetailMapper.toUserDetailDTO(userEntity);
  }
}
