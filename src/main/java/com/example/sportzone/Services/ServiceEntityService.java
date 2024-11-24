package com.example.sportzone.Services;

import com.example.sportzone.Repository.ServiceRepository;
import com.example.sportzone.entity.ServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    // Fetch services by a list of IDs
    public List<ServiceEntity> getAllServicesByIds(List<Long> ids) {
        return serviceRepository.findAllById(ids);
    }

    // Other methods...
}
