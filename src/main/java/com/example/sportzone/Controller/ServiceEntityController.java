package com.example.sportzone.Controller;

import com.example.sportzone.entity.ServiceEntity;
import com.example.sportzone.Services.ServiceEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ServiceEntityController {

    @Autowired
    private ServiceEntityService serviceEntityService;

    // Display form to create a ServiceEntity
    @GetMapping("/createServiceEntity")
    public String showCreateForm() {
        return "create_service_entity_form"; // Thymeleaf template name
    }

    // Handle form submission to create a ServiceEntity
    @PostMapping("/createServiceEntity")
    public String createServiceEntity(@RequestParam("nom") String nom, @RequestParam("description") String description, Model model) {
        // Call the service layer to create a new ServiceEntity
        ServiceEntity serviceEntity = serviceEntityService.createServiceEntity(nom, description);

        // Add the created ServiceEntity to the model to display on the confirmation page
        model.addAttribute("serviceEntity", serviceEntity);

        return "service_entity_created"; // Thymeleaf template to show the created service details
    }
}
