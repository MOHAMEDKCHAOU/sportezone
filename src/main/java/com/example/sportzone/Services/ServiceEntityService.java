package com.example.sportzone.Services;

import com.example.sportzone.entity.ServiceEntity;
import com.example.sportzone.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceEntityService {

    @Autowired
    private ServiceRepository serviceEntityRepository;

    // 1. Créer un nouveau ServiceEntity
    public ServiceEntity createServiceEntity(String nom, String description) {
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setNom(nom);
        serviceEntity.setDescription(description);

        // Persister le service dans la base de données
        return serviceEntityRepository.save(serviceEntity);
    }

    // 2. Récupérer un ServiceEntity par ID
    public ServiceEntity getServiceEntityById(Long id) {
        return serviceEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ServiceEntity introuvable avec l'ID : " + id));
    }

    // 3. Mettre à jour un ServiceEntity existant
    public ServiceEntity updateServiceEntity(Long id, ServiceEntity updatedService) {
        // Vérifier si l'entité existe
        Optional<ServiceEntity> existingEntityOptional = serviceEntityRepository.findById(id);
        if (existingEntityOptional.isPresent()) {
            ServiceEntity existingEntity = existingEntityOptional.get();

            // Mettre à jour les propriétés
            existingEntity.setNom(updatedService.getNom());
            existingEntity.setDescription(updatedService.getDescription());

            // Sauvegarder les modifications
            return serviceEntityRepository.save(existingEntity);
        } else {
            throw new RuntimeException("ServiceEntity introuvable avec l'ID : " + id);
        }
    }

    // 4. Supprimer un ServiceEntity par ID
    public void deleteServiceEntity(Long id) {
        if (serviceEntityRepository.existsById(id)) {
            serviceEntityRepository.deleteById(id);
        } else {
            throw new RuntimeException("ServiceEntity introuvable avec l'ID : " + id);
        }
    }
}
