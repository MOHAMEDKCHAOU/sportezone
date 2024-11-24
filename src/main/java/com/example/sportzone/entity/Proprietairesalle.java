package com.example.sportzone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Proprietairesalle extends Utilisateur {

    @OneToMany(mappedBy = "proprietairesalle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Salledesport> sallesdesport;

}
