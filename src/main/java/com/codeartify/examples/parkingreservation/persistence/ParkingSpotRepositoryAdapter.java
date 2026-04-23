package com.codeartify.examples.parkingreservation.persistence;

import com.codeartify.examples.parkingreservation.domain.ParkingSpot2;
import com.codeartify.examples.parkingreservation.application.ParkingSpotRepository;
import com.codeartify.examples.parkingreservation.domain.ParkingSpotId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
class ParkingSpotRepositoryAdapter implements ParkingSpotRepository {
    
    private final ParkingSpotJpaRepository spotJpaRepository;
    
    @Override
    public Optional<ParkingSpot2> findAnyAvailable() {
        return spotJpaRepository.findAnyAvailable()
                .map(ParkingSpotEntity::getId)
                .map(ParkingSpotId::new)
                .map(spotId -> new ParkingSpot2(spotId, true));
    }
}
