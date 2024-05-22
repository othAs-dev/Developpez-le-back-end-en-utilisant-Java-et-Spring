package org.openclassrooms.chatop.security.DTO;

import lombok.Data;

@Data
public class RegisterDTO {
  private String username;
  private String password;
  private String email;
}
