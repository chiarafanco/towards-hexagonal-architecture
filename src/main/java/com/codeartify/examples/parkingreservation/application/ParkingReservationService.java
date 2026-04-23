package com.codeartify.examples.parkingreservation.application;

import com.codeartify.examples.parkingreservation.domain.ParkingReservation;
import com.codeartify.examples.parkingreservation.domain.ParkingReservationException;
import com.codeartify.examples.parkingreservation.domain.ReservationPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ParkingReservationService {

    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingReservationRepository parkingReservationRepository;

    @Transactional
    public long reserveSpot(String reservedBy, ReservationPeriod reservationPeriod) {
        if (parkingReservationRepository.existsOverlap(reservedBy, reservationPeriod)) {
            throw new ParkingReservationException.Overlapping();
        }

        final var spot = parkingSpotRepository.findAnyAvailable()
                .orElseThrow(ParkingReservationException.SpotUnavailable::new);

        final var reservation = ParkingReservation.builder()
                .spotId(spot.id())
                .reservedBy(reservedBy)
                .reservationPeriod(reservationPeriod)
                .build();
        return parkingReservationRepository.save(reservation);
    }

}

