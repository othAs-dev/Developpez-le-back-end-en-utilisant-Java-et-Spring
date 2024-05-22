package org.openclassrooms.chatop.security.DTO;

import lombok.Data;

@Data
public class LoginDTO {
  private String email;
  private String password;
}
