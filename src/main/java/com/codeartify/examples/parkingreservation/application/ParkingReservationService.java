package com.codeartify.examples.parkingreservation.application;

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

    @Transactional
    public long reserveSpot(String reservedBy, ReservationPeriod period) {
        return reserveSpot(reservedBy, period.startTime(), period.endTime());
    }
    
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
        
        if (parkingReservationRepository.existsOverlap(reservedBy, startTime, endTime)) {
            throw new ParkingReservationException.Overlapping();
        }

        final var spot = parkingSpotRepository.findAnyAvailable()
                .orElseThrow(ParkingReservationException.SpotUnavailable::new);
        
        final var reservation = ParkingReservation.builder()
                .spotId(spot.id())
                .reservedBy(reservedBy)
                .startTime(startTime)
                .endTime(endTime)
                .build();
        return parkingReservationRepository.save(reservation);
    }
}

