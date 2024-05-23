package org.openclassrooms.chatop.security.DTO;

import lombok.Data;

@Data
public class RegisterDTO {
  private String email;
  private String password;
  private String name;
}
