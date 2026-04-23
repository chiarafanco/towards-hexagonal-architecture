package com.codeartify.examples.parkingreservation.persistence;

import com.codeartify.examples.parkingreservation.application.ParkingSpotRepository;
import com.codeartify.examples.parkingreservation.domain.ParkingSpot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
class ParkingSpotRepositoryAdapter implements ParkingSpotRepository {
    
    private final ParkingSpotJpaRepository spotJpaRepository;
    
    @Override
    public Optional<ParkingSpot> findAnyAvailable() {
        return spotJpaRepository.findAnyAvailable()
                .map(ParkingSpotMapper::map);
    }
}
