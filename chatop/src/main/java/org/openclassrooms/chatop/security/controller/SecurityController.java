package org.openclassrooms.chatop.security.controller;

import lombok.AllArgsConstructor;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;
import org.openclassrooms.chatop.user.repository.UserDetailRepository;
import org.openclassrooms.chatop.user.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class SecurityController {

  private AuthenticationManager authenticationManager;

  private JwtEncoder jwtEncoder;

  private UserDetailRepository userDetailRepository;

  private UserService userService;

  @PostMapping("/login")
  public Map<String, String> login(String username, String password) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(username, password)
    );
    Instant now = Instant.now();
    String scope = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
    JwtClaimsSet claims = JwtClaimsSet.builder()
      .issuedAt(now)
      .expiresAt(now.plus(10, ChronoUnit.MINUTES))
      .subject(username)
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
  public UserDetailEntity register(String username, String password, String email) {
    return userService.addNewUser(username, password, email);
  }

  @GetMapping("/me")
  public UserDetailEntity getUserDetails(Authentication authentication) {
    String username = authentication.getName();
    return userDetailRepository.findByUsername(username);
  }
}
