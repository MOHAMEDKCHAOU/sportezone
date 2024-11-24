package com.example.sportzone.Services;

import com.example.sportzone.entity.Abonnement;
import com.example.sportzone.Repository.AbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbonnementService {

    @Autowired
    private AbonnementRepository abonnementRepository;

    // 1. Create an abonnement
    public Abonnement createAbonnement(Abonnement abonnement) {
        // Save the abonnement and return the saved object
        return abonnementRepository.save(abonnement);
    }

    // 2. Get an abonnement by its ID
    public Optional<Abonnement> getAbonnementById(Long abonnementId) {
        return abonnementRepository.findById(abonnementId);
    }

    // 3. Get all abonnements for a specific Salledesport (Gym)
    public List<Abonnement> getAbonnementsBySalle(Long salleId) {
        return abonnementRepository.findBySalledesportId(salleId);
    }

    // 4. Delete an abonnement by its ID
    public void deleteAbonnement(Long abonnementId) {
        abonnementRepository.deleteById(abonnementId);
    }

    // 5. Update an existing abonnement
    public Abonnement updateAbonnement(Long abonnementId, Abonnement abonnement) {
        Optional<Abonnement> existingAbonnement = abonnementRepository.findById(abonnementId);
        if (existingAbonnement.isPresent()) {
            abonnement.setId(abonnementId);  // Ensure the ID is set correctly
            return abonnementRepository.save(abonnement);
        } else {
            throw new RuntimeException("Abonnement not found");
        }
    }
}
