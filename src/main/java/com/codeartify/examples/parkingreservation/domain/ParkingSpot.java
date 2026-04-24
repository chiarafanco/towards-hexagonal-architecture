package com.codeartify.examples.parkingreservation.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ParkingSpot {
    
    private final ParkingSpotId id;
    private final ParkingSpotStatus status;

    public ParkingSpot reserve() {
        if (status != ParkingSpotStatus.AVAILABLE) {
            throw new IllegalStateException("Cannot reserve an unavailable spot");
        }
        return new ParkingSpot(id, ParkingSpotStatus.RESERVED);
    }
}
