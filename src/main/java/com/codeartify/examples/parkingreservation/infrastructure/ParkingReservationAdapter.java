package com.codeartify.examples.parkingreservation.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class ParkingReservationAdapter {
    
    private final ParkingReservationRepository parkingReservationRepository;
    
    public boolean isOverlapping(String reservedBy, LocalDateTime startTime, LocalDateTime endTime) {
        return parkingReservationRepository.hasActiveReservation(reservedBy, startTime, endTime);
    }
}
