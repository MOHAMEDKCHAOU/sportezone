package com.example.sportzone.Repository;

import com.example.sportzone.entity.Salledesport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalledesportRepository extends JpaRepository<Salledesport, Long> {
    // Method to find all salles by the owner (proprietaire)
    List<Salledesport> findByProprietairesalleId(Long proprietaireId);
}
