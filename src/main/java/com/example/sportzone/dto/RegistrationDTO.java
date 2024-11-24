package com.example.sportzone.dto;

import lombok.Data;

@Data
public class RegistrationDTO {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String adresse;
    private String usertype;  // "client" or "proprietaireSalle"
    private String telephone; // Specific to Client


    }

