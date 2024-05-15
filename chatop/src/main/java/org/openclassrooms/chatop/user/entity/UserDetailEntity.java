package org.openclassrooms.chatop.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.*;
import jakarta.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@Table(name = "user_detail")
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "email", unique = true)
    @Email
    @NotNull
    private String email;

    @Size(min = 8, max = 32)
    @NotEmpty
    @NotNull
    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
