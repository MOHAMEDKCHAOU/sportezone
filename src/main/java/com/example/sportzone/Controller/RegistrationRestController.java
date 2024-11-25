package com.example.sportzone.Controller;

import com.example.sportzone.dto.RegistrationDTO;
import com.example.sportzone.entity.Utilisateur;
import com.example.sportzone.Services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") // Préfixe pour toutes les routes utilisateurs
public class RegistrationRestController {

    @Autowired
    private UtilisateurService utilisateurService;

    // 1. Enregistrer un utilisateur
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationDTO registrationDTO) {
        try {
            utilisateurService.registerUser(registrationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Utilisateur enregistré avec succès !");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de l'enregistrement : " + e.getMessage());
        }
    }

    // 2. Obtenir la liste de tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUsers() {
        List<Utilisateur> users = utilisateurService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(users);
    }

    // 3. Obtenir les détails d'un utilisateur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUserById(@PathVariable Long id) {
        try {
            Utilisateur user = utilisateurService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 4. Supprimer un utilisateur par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            utilisateurService.deleteUser(id);
            return ResponseEntity.ok("Utilisateur supprimé avec succès !");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur introuvable !");
        }
    }
}
