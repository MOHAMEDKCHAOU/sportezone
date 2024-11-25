package com.example.sportzone.Controller;

import com.example.sportzone.entity.Abonnement;
import com.example.sportzone.entity.Proprietairesalle;
import com.example.sportzone.entity.Salledesport;
import com.example.sportzone.Services.AbonnementService;
import com.example.sportzone.Services.ProprietairesalleService;
import com.example.sportzone.Services.SalledesportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proprietaireSalle")
public class AbonnementRestController {

    @Autowired
    private AbonnementService abonnementService;

    @Autowired
    private ProprietairesalleService proprietairesalleService;

    @Autowired
    private SalledesportService salledesportService;

    // 1. Créer un abonnement
    @PostMapping("/createAbonnement/{proprietaireId}/{salleId}")
    public ResponseEntity<Abonnement> createAbonnement(@PathVariable Long proprietaireId,
                                                       @PathVariable Long salleId,
                                                       @RequestBody Abonnement abonnement) {
        try {
            Proprietairesalle proprietaire = proprietairesalleService.getProprietairesalleById(proprietaireId)
                    .orElseThrow(() -> new RuntimeException("Propriétaire non trouvé"));

            Salledesport salle = salledesportService.getSalleById(salleId)
                    .orElseThrow(() -> new RuntimeException("Salle non trouvée"));

            abonnement.setSalledesport(salle);
            abonnement.setClient(null); // Optionnel, si non utilisé

            Abonnement createdAbonnement = abonnementService.createAbonnement(abonnement);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdAbonnement);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 2. Lister les abonnements pour une salle spécifique
    @GetMapping("/listAbonnements/{salleId}")
    public ResponseEntity<List<Abonnement>> listAbonnements(@PathVariable Long salleId) {
        try {
            List<Abonnement> abonnements = abonnementService.getAbonnementsBySalle(salleId);
            return ResponseEntity.ok(abonnements);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 3. Supprimer un abonnement
    @DeleteMapping("/deleteAbonnement/{abonnementId}")
    public ResponseEntity<Void> deleteAbonnement(@PathVariable Long abonnementId) {
        try {
            abonnementService.deleteAbonnement(abonnementId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 4. Récupérer un abonnement spécifique
    @GetMapping("/getAbonnement/{abonnementId}")
    public ResponseEntity<Abonnement> getAbonnement(@PathVariable Long abonnementId) {
        try {
            Abonnement abonnement = abonnementService.getAbonnementById(abonnementId)
                    .orElseThrow(() -> new RuntimeException("Abonnement non trouvé"));
            return ResponseEntity.ok(abonnement);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 5. Mettre à jour un abonnement
    @PutMapping("/updateAbonnement/{abonnementId}")
    public ResponseEntity<Abonnement> updateAbonnement(@PathVariable Long abonnementId,
                                                       @RequestBody Abonnement abonnement) {
        try {
            abonnementService.updateAbonnement(abonnementId, abonnement);
            return ResponseEntity.ok(abonnement);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
