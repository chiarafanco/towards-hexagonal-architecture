package com.codeartify.examples.parkingreservation.domain;

public record ParkingSpotId(long value) {
    
    public static ParkingSpotId of(long value) {
        return new ParkingSpotId(value);
    }
}
