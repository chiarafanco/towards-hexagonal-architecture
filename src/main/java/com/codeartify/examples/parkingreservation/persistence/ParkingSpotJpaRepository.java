package com.codeartify.examples.parkingreservation.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface ParkingSpotJpaRepository extends JpaRepository<ParkingSpotEntity, Long> {

    @Query("""
            SELECT p
            FROM ParkingSpot p
            WHERE p.isAvailable = true
            ORDER BY FUNCTION('RAND')
            LIMIT 1
            """)
    Optional<ParkingSpotEntity> findRandomAvailable();
}
