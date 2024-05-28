package org.openclassrooms.chatop.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.openclassrooms.chatop.exceptions.ApiException;
import org.openclassrooms.chatop.security.DTO.LoginDTO;
import org.openclassrooms.chatop.security.DTO.RegisterDTO;
import org.openclassrooms.chatop.user.DTO.UserDetailDTO;
import org.openclassrooms.chatop.security.utils.generators.GenerateToken;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;
import org.openclassrooms.chatop.user.mapper.UserDetailMapper;
import org.openclassrooms.chatop.user.repository.UserDetailRepository;
import org.openclassrooms.chatop.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Security")
@AllArgsConstructor
public class SecurityController {

  private final AuthenticationManager authenticationManager;
  private final UserDetailRepository userDetailRepository;
  private final UserService userService;
  private final GenerateToken generateToken;

  /**
   * Authenticates a user and generates a JWT token.
   *
   * @param loginRequest the login request containing the user's email and password
   * @return a map containing the generated JWT token
   * @throws ApiException.BadRequestException if the login fails
   */
  @Operation(summary = "This method is used to login")
  @PostMapping("/login")
  public Map<String, String> login(@RequestBody LoginDTO loginRequest) {
    try {
      Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
      );
      String jwt = generateToken.generateTokenFunction(authentication);
      return Map.of("token", jwt);
    } catch (Exception e) {
      throw new ApiException.BadRequestException("Failed to login user the error is: " + e.getMessage());
    }

  }

  /**
   * Registers a new user and generates a JWT token.
   *
   * @param registerRequest the registration request containing the user's name, email, and password
   * @return a map containing the generated JWT token
   * @throws ApiException.BadRequestException if the registration fails
   */
  @Operation(summary = "This method is used to register")
  @PostMapping("/register")
  public Map<String, String> register(@RequestBody RegisterDTO registerRequest) {

    try {
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
    } catch (Exception e) {
      throw new ApiException.BadRequestException("Failed to register user the error is: " + e.getMessage());
    }
  }

  /**
   * Retrieves the details of the currently authenticated user.
   *
   * @param authentication the authentication object containing the user's details
   * @return a ResponseEntity containing the UserDetailDTO of the authenticated user
   * @throws ApiException.NotFoundException if the user is not found
   */
  @Operation(summary = "This method is used to get user details who is logged in")
  @GetMapping("/me")
  public ResponseEntity<UserDetailDTO> getUserDetails(Authentication authentication) {
    String email = authentication.getName();
    UserDetailEntity userEntity = userDetailRepository.findByEmail(email);
    if (userEntity == null) {
      throw new ApiException.NotFoundException("User not found");
    }
    return ResponseEntity.ok().body(UserDetailMapper.toDTO(userEntity));
  }
}

