package com.codeartify.examples.parkingreservation.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ParkingSpot {
    
    private final ParkingSpotId id;
    private final ParkingSpotStatus status;

    public ParkingSpot reserve() {
        return new ParkingSpot(id, ParkingSpotStatus.RESERVED);
    }
}
