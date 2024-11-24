package com.example.sportzone.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String type;
    private String description;
    private Double prix;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "salle_id")
    private Salledesport salledesport;

    @OneToMany(mappedBy = "abonnement", cascade = CascadeType.ALL)
    private List<Facture> factures;

    @OneToMany(mappedBy = "abonnement", cascade = CascadeType.ALL)
    private List<ServiceEntity> services;

}
