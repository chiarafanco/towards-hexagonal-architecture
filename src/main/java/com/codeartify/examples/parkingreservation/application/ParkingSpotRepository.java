package com.codeartify.examples.parkingreservation.application;

import com.codeartify.examples.parkingreservation.domain.ParkingSpot2;

import java.util.Optional;

public interface ParkingSpotRepository {
    
    Optional<ParkingSpot2> findAnyAvailable();
}
