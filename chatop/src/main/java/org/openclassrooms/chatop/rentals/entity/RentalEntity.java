package org.openclassrooms.chatop.rentals.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@Table(name = "rental")
@NoArgsConstructor
@AllArgsConstructor
public class RentalEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name", unique = true)
  @Size(min = 2, max = 32)
  private String name;

  @Column(name = "description")
  @Size(max = 255)
  private String description;

  @Column(name = "surface")
  private int surface;

  @Column(name = "price")
  private double price;

  @Column(name = "picture")
  private String picture;

  @ManyToOne
  @JoinColumn(name = "owner_id", nullable = false)
  private UserDetailEntity owner;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
