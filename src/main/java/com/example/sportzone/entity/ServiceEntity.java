package com.example.sportzone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor  // Lombok generates constructor with all fields
@NoArgsConstructor   // Lombok generates no-args constructor
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;

    @ManyToOne
    @JoinColumn(name = "abbonement_id")
    private Abonnement abonnement;  // This can be null, no problem
}
