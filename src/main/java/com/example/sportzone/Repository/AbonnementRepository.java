package com.example.sportzone.Repository;

import com.example.sportzone.entity.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
    List<Abonnement> findBySalledesportId(Long salleId);
}
