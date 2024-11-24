package com.example.sportzone.Services;

import com.example.sportzone.dto.RegistrationDTO;
import com.example.sportzone.entity.Client;
import com.example.sportzone.entity.Proprietairesalle;
import com.example.sportzone.entity.Utilisateur;
import com.example.sportzone.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository; // Repository for database interactions

    // Register a new user (either Client or Proprietairesalle)
    public void registerUser(RegistrationDTO registrationDTO) {
        Utilisateur utilisateur = createUtilisateurFromDTO(registrationDTO);

        // Directly set the plain-text password (NOT RECOMMENDED FOR PRODUCTION)
        utilisateur.setPassword(registrationDTO.getPassword());

        // Save the new user to the database
        utilisateurRepository.save(utilisateur);
    }

    // Create a user based on registration DTO, factoring in the user type
    private Utilisateur createUtilisateurFromDTO(RegistrationDTO registrationDTO) {
        Utilisateur utilisateur;

        switch (registrationDTO.getUsertype().toUpperCase()) {
            case "CLIENT":
                utilisateur = createClientFromDTO(registrationDTO);
                break;
            case "PROPRIETAIRESALLE":
                utilisateur = createProprietairesalleFromDTO(registrationDTO);
                break;
            default:
                throw new IllegalArgumentException("Invalid user type: " + registrationDTO.getUsertype());
        }

        // Set common fields for all users
        utilisateur.setNom(registrationDTO.getNom());
        utilisateur.setPrenom(registrationDTO.getPrenom());
        utilisateur.setEmail(registrationDTO.getEmail());
        utilisateur.setAdresse(registrationDTO.getAdresse());

        return utilisateur;
    }

    // Helper method to create a Client user
    private Client createClientFromDTO(RegistrationDTO registrationDTO) {
        Client client = new Client();
        client.setTelephone(registrationDTO.getTelephone()); // Set specific client info
        return client;
    }

    // Helper method to create a Proprietairesalle user
    private Proprietairesalle createProprietairesalleFromDTO(RegistrationDTO registrationDTO) {
        return new Proprietairesalle(); // Proprietairesalle might not have specific fields in DTO, adjust as needed
    }

    // Retrieve all users from the database
    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    // Get a user by their ID
    public Utilisateur getUserById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Delete a user by their ID
    public void deleteUser(Long id) {
        utilisateurRepository.deleteById(id);
    }

    // Retrieve a user by their email (used for login)
    public Optional<Utilisateur> getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email);  // Assuming this method exists in your repository
    }

    // Authenticate a user by comparing the password entered with the plain-text password stored in the database
    public boolean authenticateUser(String email, String password) {
        Optional<Utilisateur> utilisateurOpt = getUtilisateurByEmail(email);
        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
            // Compare the provided password with the stored plain-text password
            return password.equals(utilisateur.getPassword());
        }
        return false;  // Return false if the user is not found
    }
}
