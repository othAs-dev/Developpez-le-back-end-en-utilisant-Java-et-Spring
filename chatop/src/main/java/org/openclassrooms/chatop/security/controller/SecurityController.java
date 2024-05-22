package org.openclassrooms.chatop.security.controller;

import lombok.AllArgsConstructor;
import org.openclassrooms.chatop.security.DTO.LoginDTO;
import org.openclassrooms.chatop.security.DTO.RegisterDTO;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;
import org.openclassrooms.chatop.user.repository.UserDetailRepository;
import org.openclassrooms.chatop.user.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class SecurityController {

  private final AuthenticationManager authenticationManager;
  private final JwtEncoder jwtEncoder;
  private final UserDetailRepository userDetailRepository;
  private final UserService userService;

  @PostMapping("/login")
  public Map<String, String> login(@RequestBody LoginDTO loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
    );
    Instant now = Instant.now();
    String scope = authentication.getAuthorities().stream()
      .map(GrantedAuthority::getAuthority)
      .collect(Collectors.joining(" "));
    JwtClaimsSet claims = JwtClaimsSet.builder()
      .issuedAt(now)
      .expiresAt(now.plus(10, ChronoUnit.MINUTES))
      .subject(loginRequest.getEmail())
      .claim("scope", scope)
      .build();
    JwtEncoderParameters parameters = JwtEncoderParameters.from(
      JwsHeader.with(MacAlgorithm.HS256).build(),
      claims
    );

    String jwt = jwtEncoder.encode(parameters).getTokenValue();
    return Map.of("token", jwt);
  }

  @PostMapping("/register")
  public UserDetailEntity register(@RequestBody RegisterDTO registerRequest) {
    return userService.addNewUser(
      registerRequest.getUsername(),
      registerRequest.getPassword(),
      registerRequest.getEmail()
    );
  }

  @GetMapping("/me")
  public UserDetailEntity getUserDetails(Authentication authentication) {
    String email = authentication.getName();
    return userDetailRepository.findByEmail(email);
  }
}
