package com.example.sportzone.Repository;

import com.example.sportzone.entity.Proprietairesalle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProprietairesalleRepository extends JpaRepository<Proprietairesalle, Long> {

    // Custom method to find Proprietairesalle by name (nom)
    List<Proprietairesalle> findByNom(String nom);  // Finds Proprietairesalle by the 'nom' field

}
