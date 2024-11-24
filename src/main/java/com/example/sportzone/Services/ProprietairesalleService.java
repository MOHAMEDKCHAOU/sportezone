package com.example.sportzone.Services;

import com.example.sportzone.Repository.ProprietairesalleRepository;
import com.example.sportzone.entity.Proprietairesalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProprietairesalleService {

    @Autowired
    private ProprietairesalleRepository proprietairesalleRepository;

    // 1. Create a new Proprietairesalle
    @Transactional
    public Proprietairesalle createProprietairesalle(Proprietairesalle proprietairesalle) {
        return proprietairesalleRepository.save(proprietairesalle);  // Save the Proprietairesalle entity, which includes the name (nom)
    }

    // 2. Get all Proprietairesalle
    public List<Proprietairesalle> getAllProprietairesalle() {
        List<Proprietairesalle> proprietairesalleList = new ArrayList<>();
        proprietairesalleRepository.findAll().forEach(proprietairesalleList::add);
        return proprietairesalleList;  // Return a list of Proprietairesalle
    }

    // 3. Get Proprietairesalle by ID
    public Optional<Proprietairesalle> getProprietairesalleById(Long id) {
        return proprietairesalleRepository.findById(id);  // Fetch by ID (Long), returns Optional
    }

    // 4. Get Proprietairesalle by Name (nom)
    public List<Proprietairesalle> getProprietairesalleByName(String nom) {
        return proprietairesalleRepository.findByNom(nom);  // Assuming you have a findByNom method in the repository
    }

    // 5. Update an existing Proprietairesalle
    @Transactional
    public Proprietairesalle updateProprietairesalle(Proprietairesalle proprietairesalle) {
        return proprietairesalleRepository.save(proprietairesalle);  // Save updated Proprietairesalle, including name (nom)
    }

    // 6. Delete Proprietairesalle by ID
    @Transactional
    public void deleteProprietairesalle(Long id) {
        if (proprietairesalleRepository.existsById(id)) {
            proprietairesalleRepository.deleteById(id);  // Delete by ID
        } else {
            throw new RuntimeException("Proprietairesalle not found with id: " + id);  // If not found, throw an exception
        }
    }
}
