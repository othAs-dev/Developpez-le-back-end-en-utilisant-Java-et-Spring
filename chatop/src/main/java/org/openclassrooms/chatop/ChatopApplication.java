package org.openclassrooms.chatop;

import org.openclassrooms.chatop.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatopApplication {

  public static void main(String[] args) {
    SpringApplication.run(ChatopApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunnerUserDetails(UserService userService) {
    return args -> {
      userService.addNewUser("administrateur", "password", "admin@chatop.fr");
      userService.addNewUser("user1", "password", "user1@chatop.fr");
    };
  }
}
