package com.codeartify.examples.parkingreservation.service;

import com.codeartify.examples.parkingreservation.model.ParkingSpot;

import java.util.Optional;

public interface ParkingSpotRepository {
    
    Optional<ParkingSpot> findAnyAvailable();
}
