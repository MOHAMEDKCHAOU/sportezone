package com.example.sportzone.Services;

import com.example.sportzone.entity.Proprietairesalle;
import com.example.sportzone.entity.Salledesport;
import com.example.sportzone.Repository.SalledesportRepository;
import com.example.sportzone.Repository.ProprietairesalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalledesportService {

    @Autowired
    private SalledesportRepository salledesportRepository;

    @Autowired
    private ProprietairesalleRepository proprietairesalleRepository;

    // 1. Create a new salle de sport
    public Salledesport createSalle(Long proprietaireId, Salledesport salledesport) {
        Optional<Proprietairesalle> proprietaireOptional = proprietairesalleRepository.findById(proprietaireId);
        if (proprietaireOptional.isPresent()) {
            Proprietairesalle proprietaire = proprietaireOptional.get();

            // Associer la salle au propriétaire
            salledesport.setProprietairesalle(proprietaire);

            // Sauvegarder la salle et retourner l'entité sauvegardée
            return salledesportRepository.save(salledesport);
        } else {
            throw new RuntimeException("Propriétaire non trouvé !");
        }
    }

    // 2. List all salles by proprietaire ID
    public List<Salledesport> getSallesByProprietaire(Long proprietaireId) {
        return salledesportRepository.findByProprietairesalleId(proprietaireId);
    }

    // 3. Get salle by its ID
    public Optional<Salledesport> getSalleById(Long salleId) {
        return salledesportRepository.findById(salleId);
    }

    // 4. Update an existing salle
    public Salledesport updateSalle(Long salleId, Salledesport salledesport) {
        Optional<Salledesport> existingSalleOptional = salledesportRepository.findById(salleId);

        if (existingSalleOptional.isPresent()) {
            Salledesport existingSalle = existingSalleOptional.get();

            // Update the properties
            existingSalle.setNomSalle(salledesport.getNomSalle());
            existingSalle.setAdresse(salledesport.getAdresse());
            existingSalle.setNumTel(salledesport.getNumTel());
            existingSalle.setHeureOuverture(salledesport.getHeureOuverture());
            existingSalle.setHeureFermeture(salledesport.getHeureFermeture());

            // Save the updated salle
            return salledesportRepository.save(existingSalle);
        } else {
            throw new RuntimeException("Salle non trouvée !"); // Salle not found
        }
    }

    // 5. Delete a salle by its ID
    public void deleteSalle(Long salleId) {
        salledesportRepository.deleteById(salleId);
    }
}
