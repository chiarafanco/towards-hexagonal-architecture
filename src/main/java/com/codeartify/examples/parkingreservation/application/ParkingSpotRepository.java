package com.codeartify.examples.parkingreservation.application;

import java.util.Optional;

public interface ParkingSpotRepository {
    
    Optional<ParkingSpot> findAnyAvailable();
}
