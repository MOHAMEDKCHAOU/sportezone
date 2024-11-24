package com.example.sportzone.Services;

import com.example.sportzone.entity.ServiceEntity;
import com.example.sportzone.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceEntityService {

    @Autowired
    private ServiceRepository serviceEntityRepository;

    // Method to create a new ServiceEntity
    public ServiceEntity createServiceEntity(String nom, String description) {
        // Create a new ServiceEntity using the no-args constructor
        ServiceEntity serviceEntity = new ServiceEntity();

        // Manually set the properties
        serviceEntity.setNom(nom);
        serviceEntity.setDescription(description);
        serviceEntity.setAbonnement(null); // Set abonnement to null or some other valid object

        // Save the ServiceEntity in the database
        return serviceEntityRepository.save(serviceEntity);
    }
}
