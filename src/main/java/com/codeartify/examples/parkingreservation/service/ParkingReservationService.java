package com.codeartify.examples.parkingreservation.service;

import com.codeartify.examples.parkingreservation.infrastructure.ParkingReservationAdapter;
import com.codeartify.examples.parkingreservation.model.ParkingReservation;
import com.codeartify.examples.parkingreservation.infrastructure.ParkingReservationRepository;
import com.codeartify.examples.parkingreservation.infrastructure.ParkingSpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class ParkingReservationService {

    private static final LocalTime OPENING_TIME = LocalTime.of(6, 0); // 6:00 AM
    private static final LocalTime CLOSING_TIME = LocalTime.of(22, 0); // 10:00 PM
    
    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingReservationRepository parkingReservationRepository;
    private final ParkingReservationAdapter parkingReservationAdapter;

    @Transactional
    public long reserveSpot(String reservedBy, LocalDateTime startTime, LocalDateTime endTime) {
        if (endTime.isBefore(startTime)) {
            throw new ParkingReservationException.EndBeforeStart();
        }

        if (Duration.between(startTime, endTime).toMinutes() < 30) {
            throw new ParkingReservationException.DurationTooShort();
        }

        if (startTime.toLocalTime().isBefore(OPENING_TIME) || endTime.toLocalTime().isAfter(CLOSING_TIME)) {
            throw new ParkingReservationException.OutsideOperatingHours();
        }

        final var overlapping = parkingReservationAdapter.isOverlapping(reservedBy, startTime, endTime);
        if (overlapping) {
            throw new ParkingReservationException.Overlapping();
        }

        final var spot = parkingSpotRepository.findAnyAvailableSpot();
        if (spot == null) {
            throw new ParkingReservationException.SpotUnavailable();
        }

        final var reservation = new ParkingReservation(
                reservedBy,
                spot.getId(),
                startTime,
                endTime);
        parkingReservationRepository.save(reservation);

        spot.setAvailable(false);
        parkingSpotRepository.save(spot);
        
        return reservation.getId();
    }

}

