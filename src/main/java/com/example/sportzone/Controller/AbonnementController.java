package com.example.sportzone.Controller;

import com.example.sportzone.entity.Abonnement;
import com.example.sportzone.entity.Proprietairesalle;
import com.example.sportzone.entity.Salledesport;
import com.example.sportzone.Services.AbonnementService;
import com.example.sportzone.Services.ProprietairesalleService;
import com.example.sportzone.Services.SalledesportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/proprietaireSalle")
public class AbonnementController {

    @Autowired
    private AbonnementService abonnementService;

    @Autowired
    private ProprietairesalleService proprietairesalleService;

    @Autowired
    private SalledesportService salledesportService;

    // 1. Afficher le formulaire pour créer un abonnement pour une salle spécifique
    @GetMapping("/createAbonnement/{proprietaireId}/{salleId}")
    public String showCreateAbonnementForm(@PathVariable Long proprietaireId, @PathVariable Long salleId, Model model) {
        // Create a new Abonnement object
        Abonnement abonnement = new Abonnement();

        // Get the ProprietaireSalle and Salledesport
        Proprietairesalle proprietaire = proprietairesalleService.getProprietairesalleById(proprietaireId)
                .orElseThrow(() -> new RuntimeException("Propriétaire not found"));

        Salledesport salle = salledesportService.getSalleById(salleId)
                .orElseThrow(() -> new RuntimeException("Salle not found"));

        // Pass the data to the model
        model.addAttribute("abonnement", abonnement);
        model.addAttribute("proprietaire", proprietaire);
        model.addAttribute("salle", salle);
        model.addAttribute("proprietaireId", proprietaireId);
        model.addAttribute("salleId", salleId);

        return "create_abonnement_form"; // Thymeleaf template for creating an abonnement
    }

    // 2. Créer un abonnement pour un client dans une salle
    @PostMapping("/createAbonnement/{proprietaireId}/{salleId}")
    public String createAbonnement(@PathVariable Long proprietaireId, @PathVariable Long salleId, @ModelAttribute("abonnement") Abonnement abonnement) {
        try {
            // Get Proprietaire and Salle from the database
            Proprietairesalle proprietaire = proprietairesalleService.getProprietairesalleById(proprietaireId)
                    .orElseThrow(() -> new RuntimeException("Propriétaire not found"));

            Salledesport salle = salledesportService.getSalleById(salleId)
                    .orElseThrow(() -> new RuntimeException("Salle not found"));

            // Set the salle (do not set client as it can remain null)
            abonnement.setSalledesport(salle);

            // Optionally, you can set client to null explicitly if needed
            abonnement.setClient(null);

            // Save the abonnement using the service
            abonnementService.createAbonnement(abonnement);

            // Redirect to the list of abonnements for this salle
            return "redirect:/proprietaireSalle/listAbonnements/" + proprietaireId + "/" + salleId;
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception if there is one
            return "error";  // Return a custom error page or redirect to an error page
        }
    }

    // 3. List Abonnements for a specific salle
    @GetMapping("/listAbonnements/{proprietaireId}/{salleId}")
    public String listAbonnements(@PathVariable Long proprietaireId, @PathVariable Long salleId, Model model) {
        // Get the list of abonnements for a specific salle
        List<Abonnement> abonnements = abonnementService.getAbonnementsBySalle(salleId);

        // Get the ProprietaireSalle and Salledesport
        Proprietairesalle proprietaire = proprietairesalleService.getProprietairesalleById(proprietaireId)
                .orElseThrow(() -> new RuntimeException("Propriétaire not found"));

        Salledesport salle = salledesportService.getSalleById(salleId)
                .orElseThrow(() -> new RuntimeException("Salle not found"));

        // Pass the abonnements, proprietor name, and salle to the view
        model.addAttribute("proprietaire", proprietaire);
        model.addAttribute("salle", salle);
        model.addAttribute("abonnements", abonnements);
        model.addAttribute("proprietaireId", proprietaireId);
        model.addAttribute("salleId", salleId);

        return "list_abonnements"; // Thymeleaf template for listing abonnements
    }

    // 4. Supprimer un abonnement par son ID
    @GetMapping("/deleteAbonnement/{proprietaireId}/{salleId}/{abonnementId}")
    public String deleteAbonnement(@PathVariable Long proprietaireId, @PathVariable Long salleId, @PathVariable Long abonnementId) {
        abonnementService.deleteAbonnement(abonnementId);
        return "redirect:/proprietaireSalle/listAbonnements/" + proprietaireId + "/" + salleId;
    }

    // 5. Afficher le formulaire pour éditer un abonnement
    @GetMapping("/editAbonnement/{proprietaireId}/{salleId}/{abonnementId}")
    public String showEditAbonnementForm(@PathVariable Long proprietaireId, @PathVariable Long salleId, @PathVariable Long abonnementId, Model model) {
        Abonnement abonnement = abonnementService.getAbonnementById(abonnementId)
                .orElseThrow(() -> new RuntimeException("Abonnement not found"));

        Proprietairesalle proprietaire = proprietairesalleService.getProprietairesalleById(proprietaireId)
                .orElseThrow(() -> new RuntimeException("Propriétaire not found"));

        Salledesport salle = salledesportService.getSalleById(salleId)
                .orElseThrow(() -> new RuntimeException("Salle not found"));

        model.addAttribute("abonnement", abonnement);
        model.addAttribute("proprietaire", proprietaire);
        model.addAttribute("salle", salle);
        model.addAttribute("proprietaireId", proprietaireId);
        model.addAttribute("salleId", salleId);

        return "edit_abonnement_form"; // Thymeleaf template for editing an abonnement
    }

    // 6. Mettre à jour un abonnement
    @PostMapping("/editAbonnement/{proprietaireId}/{salleId}/{abonnementId}")
    public String updateAbonnement(@PathVariable Long proprietaireId, @PathVariable Long salleId, @PathVariable Long abonnementId, @ModelAttribute("abonnement") Abonnement abonnement) {
        abonnement.setSalledesport(salledesportService.getSalleById(salleId)
                .orElseThrow(() -> new RuntimeException("Salle not found")));

        abonnementService.updateAbonnement(abonnementId, abonnement);

        // Redirect to the list of abonnements after update
        return "redirect:/proprietaireSalle/listAbonnements/" + proprietaireId + "/" + salleId;
    }
}
