package com.codeartify.examples.parkingreservation.persistence;

import com.codeartify.examples.parkingreservation.domain.ParkingSpot;
import com.codeartify.examples.parkingreservation.domain.ParkingSpotId;
import com.codeartify.examples.parkingreservation.domain.ParkingSpotStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class ParkingSpotMapper {
    
    public static ParkingSpot map(ParkingSpotEntity spotEntity) {
        final var spotId = new ParkingSpotId(spotEntity.getId());
        final var spotStatus = spotEntity.isAvailable()
                ? ParkingSpotStatus.AVAILABLE
                : ParkingSpotStatus.RESERVED;
        return new ParkingSpot(spotId, spotStatus);
    }

    public static ParkingSpotEntity map(ParkingSpot spot) {
        final var spotEntity = new ParkingSpotEntity();
        spotEntity.setId(spot.getId().value());
        spotEntity.setAvailable(spot.getStatus() == ParkingSpotStatus.AVAILABLE);
        return spotEntity;
    }
}