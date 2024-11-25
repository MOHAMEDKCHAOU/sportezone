package com.example.sportzone.Controller;

import com.example.sportzone.entity.Salledesport;
import com.example.sportzone.Services.SalledesportService;
import com.example.sportzone.Services.ProprietairesalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proprietaireSalle")
public class ProprietaireSalleRestController {

    @Autowired
    private SalledesportService salledesportService;

    @Autowired
    private ProprietairesalleService proprietairesalleService;

    // 1. Créer une nouvelle salle de sport
    @PostMapping("/createSalle/{proprietaireId}")
    public ResponseEntity<Salledesport> createSalle(@PathVariable Long proprietaireId,
                                                    @RequestBody Salledesport salledesport) {
        try {
            Salledesport createdSalle = salledesportService.createSalle(proprietaireId, salledesport);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSalle);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 2. Lister les salles pour un propriétaire spécifique
    @GetMapping("/listSalles/{proprietaireId}")
    public ResponseEntity<List<Salledesport>> listSalles(@PathVariable Long proprietaireId) {
        try {
            List<Salledesport> salles = salledesportService.getSallesByProprietaire(proprietaireId);
            return ResponseEntity.ok(salles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 3. Supprimer une salle par son ID
    @DeleteMapping("/deleteSalle/{salleId}")
    public ResponseEntity<Void> deleteSalle(@PathVariable Long salleId) {
        try {
            salledesportService.deleteSalle(salleId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 4. Récupérer les détails d'une salle spécifique
    @GetMapping("/getSalle/{salleId}")
    public ResponseEntity<Salledesport> getSalle(@PathVariable Long salleId) {
        try {
            Salledesport salle = salledesportService.getSalleById(salleId)
                    .orElseThrow(() -> new RuntimeException("Salle non trouvée"));
            return ResponseEntity.ok(salle);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 5. Mettre à jour une salle de sport
    @PutMapping("/updateSalle/{salleId}")
    public ResponseEntity<Salledesport> updateSalle(@PathVariable Long salleId,
                                                    @RequestBody Salledesport salledesport) {
        try {
            Salledesport updatedSalle = salledesportService.updateSalle(salleId, salledesport);
            return ResponseEntity.ok(updatedSalle);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
