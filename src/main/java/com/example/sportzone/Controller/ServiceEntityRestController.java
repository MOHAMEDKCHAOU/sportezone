package com.example.sportzone.Controller;

import com.example.sportzone.entity.ServiceEntity;
import com.example.sportzone.Services.ServiceEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/services")
public class ServiceEntityRestController {

    @Autowired
    private ServiceEntityService serviceEntityService;

    // 1. Créer un nouveau ServiceEntity
    @PostMapping
    public ResponseEntity<ServiceEntity> createServiceEntity(@RequestBody ServiceEntity serviceEntity) {
        try {
            ServiceEntity createdService = serviceEntityService.createServiceEntity(serviceEntity.getNom(), serviceEntity.getDescription());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdService);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 2. Récupérer les détails d'un ServiceEntity par ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceEntity> getServiceEntityById(@PathVariable Long id) {
        try {
            ServiceEntity serviceEntity = serviceEntityService.getServiceEntityById(id);
            return ResponseEntity.ok(serviceEntity);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 3. Supprimer un ServiceEntity par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServiceEntity(@PathVariable Long id) {
        try {
            serviceEntityService.deleteServiceEntity(id);
            return ResponseEntity.ok("Service supprimé avec succès !");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service introuvable !");
        }
    }

    // 4. Mettre à jour un ServiceEntity
    @PutMapping("/{id}")
    public ResponseEntity<ServiceEntity> updateServiceEntity(@PathVariable Long id, @RequestBody ServiceEntity updatedServiceEntity) {
        try {
            ServiceEntity updatedService = serviceEntityService.updateServiceEntity(id, updatedServiceEntity);
            return ResponseEntity.ok(updatedService);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
