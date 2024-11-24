package com.example.sportzone.Repository;

import com.example.sportzone.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    // Custom queries can be added here if needed
}
