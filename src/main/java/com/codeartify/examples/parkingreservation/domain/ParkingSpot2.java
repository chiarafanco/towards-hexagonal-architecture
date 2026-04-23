package com.codeartify.examples.parkingreservation.domain;

import lombok.NonNull;

public record ParkingSpot2(@NonNull ParkingSpotId id,
                           boolean isAvailable) {
}
