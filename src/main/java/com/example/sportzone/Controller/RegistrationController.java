package com.example.sportzone.Controller;

import com.example.sportzone.dto.RegistrationDTO;
import com.example.sportzone.entity.Utilisateur;
import com.example.sportzone.Services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users") // Organized path for all user-related endpoints
public class RegistrationController {

    @Autowired
    private UtilisateurService utilisateurService;

    // 1. Display the registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationDTO", new RegistrationDTO());
        return "register_form";  // Return the registration form view
    }

    // 2. Save the user (Client or ProprietaireSalle)
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("registrationDTO") RegistrationDTO registrationDTO) {
        utilisateurService.registerUser(registrationDTO);
        return "redirect:/users/list";  // Redirect to the user list after registration
    }

    // 3. Get all registered users
    @GetMapping("/list")
    public String getAllUsers(Model model) {
        List<Utilisateur> users = utilisateurService.getAllUsers();
        model.addAttribute("users", users);
        return "user_list";  // Return the user list view
    }

    // 4. Get user details by ID
    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        Utilisateur user = utilisateurService.getUserById(id);
        model.addAttribute("user", user);
        return "user_detail";  // Return the user detail view
    }

    // 5. Delete a user by ID
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        utilisateurService.deleteUser(id);
        return "redirect:/users/list";  // Redirect to the user list after deletion
    }
}
