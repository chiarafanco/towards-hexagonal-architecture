package com.codeartify.examples.parkingreservation.application;

import com.codeartify.examples.parkingreservation.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ParkingReservationService {

    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingReservationRepository parkingReservationRepository;

    @Transactional
    public long reserveSpot(ReserverId reserverId, ReservationPeriod reservationPeriod) {
        if (parkingReservationRepository.existsOverlap(reserverId, reservationPeriod)) {
            throw new ParkingReservationException.Overlapping();
        }

        final var parkingSpot = parkingSpotRepository.findAnyAvailable()
                .orElseThrow(ParkingReservationException.SpotUnavailable::new);
        
        final var reservation = ParkingReservation.reserve(reserverId, parkingSpot, reservationPeriod);
        return parkingReservationRepository.save(reservation);
    }
}

