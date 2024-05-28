package org.openclassrooms.chatop.message.entity;

import jakarta.persistence.*;
import lombok.*;
import org.openclassrooms.chatop.rentals.entity.RentalEntity;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@Table(name = "message")
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDetailEntity user_id;

    @OneToOne
    @JoinColumn(name = "rental_id", nullable = false)
    private RentalEntity rental;
}
