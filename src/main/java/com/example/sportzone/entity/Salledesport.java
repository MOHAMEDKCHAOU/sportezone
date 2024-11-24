package com.example.sportzone.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Salledesport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomSalle;
    private String adresse;
    private String numTel;
    private String heureOuverture;
    private String heureFermeture;

    @ManyToOne
    @JoinColumn(name = "proprietaire_id", nullable = false) // Ensure that the proprietor is not null
    private Proprietairesalle proprietairesalle;  // Many gyms can belong to one proprietor

    @OneToMany(mappedBy = "salledesport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Abonnement> abonnements;
}
