package com.codeartify.examples.parkingreservation.application;

import com.codeartify.examples.parkingreservation.domain.ParkingSpot;

import java.util.Optional;

public interface ParkingSpotRepository {
    
    Optional<ParkingSpot> findAnyAvailable();
}
