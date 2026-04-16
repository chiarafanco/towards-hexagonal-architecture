package com.codeartify.examples.parkingreservation.repository;

import com.codeartify.examples.parkingreservation.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    @Query("SELECT p FROM ParkingSpot p WHERE p.isAvailable = true ORDER BY FUNCTION('RAND') LIMIT 1")
    ParkingSpot findAnyAvailableSpot();
}
