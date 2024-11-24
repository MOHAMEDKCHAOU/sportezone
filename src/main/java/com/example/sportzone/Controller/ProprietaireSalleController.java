package com.example.sportzone.Controller;

import com.example.sportzone.entity.Salledesport;
import com.example.sportzone.entity.Proprietairesalle;
import com.example.sportzone.Services.SalledesportService;
import com.example.sportzone.Services.ProprietairesalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/proprietaireSalle")
public class ProprietaireSalleController {

    @Autowired
    private SalledesportService salledesportService;

    @Autowired
    private ProprietairesalleService proprietairesalleService;

    // 1. Afficher le formulaire pour créer une nouvelle salle
    @GetMapping("/createSalle/{proprietaireId}")
    public String showCreateSalleForm(@PathVariable Long proprietaireId, Model model) {
        // Create a new Salledesport object
        Salledesport salle = new Salledesport();

        // Pass the object to the model
        model.addAttribute("salle", salle);
        model.addAttribute("proprietaireId", proprietaireId);

        return "create_salle_form"; // Thymeleaf template for creating a new salle
    }

    // 2. Créer une nouvelle salle de sport
    @PostMapping("/createSalle/{proprietaireId}")
    public String createSalle(@PathVariable Long proprietaireId, @ModelAttribute("salle") Salledesport salledesport) {
        salledesportService.createSalle(proprietaireId, salledesport);
        return "redirect:/proprietaireSalle/listSalles/" + proprietaireId;
    }

    // 3. Afficher la liste des salles pour un propriétaire spécifique
    @GetMapping("/listSalles/{proprietaireId}")
    public String listSalles(@PathVariable Long proprietaireId, Model model) {
        List<Salledesport> salles = salledesportService.getSallesByProprietaire(proprietaireId);

        // Get Proprietaire name and pass it to the view
        Proprietairesalle proprietaire = proprietairesalleService.getProprietairesalleById(proprietaireId)
                .orElseThrow(() -> new RuntimeException("Propriétaire not found"));
        model.addAttribute("proprietaireName", proprietaire.getNom());

        model.addAttribute("salles", salles);
        model.addAttribute("proprietaireId", proprietaireId);
        return "list_salles"; // Thymeleaf template to list salles
    }

    // 4. Supprimer une salle par son ID
    @GetMapping("/deleteSalle/{proprietaireId}/{salleId}")
    public String deleteSalle(@PathVariable Long proprietaireId, @PathVariable Long salleId) {
        salledesportService.deleteSalle(salleId);
        return "redirect:/proprietaireSalle/listSalles/" + proprietaireId;
    }

    // 5. Afficher le formulaire pour éditer une salle
    @GetMapping("/editSalle/{proprietaireId}/{salleId}")
    public String showEditSalleForm(@PathVariable Long proprietaireId, @PathVariable Long salleId, Model model) {
        Salledesport salle = salledesportService.getSalleById(salleId)
                .orElseThrow(() -> new RuntimeException("Salle not found"));

        // Get Proprietaire name and pass it to the view
        Proprietairesalle proprietaire = proprietairesalleService.getProprietairesalleById(proprietaireId)
                .orElseThrow(() -> new RuntimeException("Propriétaire not found"));
        model.addAttribute("proprietaireName", proprietaire.getNom());

        model.addAttribute("salle", salle);
        model.addAttribute("proprietaireId", proprietaireId);
        return "edit_salle_form"; // Thymeleaf template for editing the salle
    }

    // 6. Mettre à jour les informations de la salle
    @PostMapping("/editSalle/{proprietaireId}/{salleId}")
    public String updateSalle(@PathVariable Long proprietaireId, @PathVariable Long salleId, @ModelAttribute("salle") Salledesport salledesport) {
        salledesport.setId(salleId);  // Ensure the correct ID is set for update
        salledesportService.updateSalle(salleId, salledesport);
        return "redirect:/proprietaireSalle/listSalles/" + proprietaireId;
    }
}
