package com.codeartify.examples.parkingreservation.persistence;

import com.codeartify.examples.parkingreservation.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParkingSpotJpaRepository extends JpaRepository<ParkingSpotEntity, Long> {
    
    @Query("SELECT p FROM ParkingSpot p WHERE p.isAvailable = true ORDER BY FUNCTION('RAND') LIMIT 1")
    ParkingSpot findAnyAvailableSpot();
}
