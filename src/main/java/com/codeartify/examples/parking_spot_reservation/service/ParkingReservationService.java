package com.codeartify.examples.parking_spot_reservation.service;

import com.codeartify.examples.parking_spot_reservation.dto.ParkingReservationRequest;
import com.codeartify.examples.parking_spot_reservation.dto.ParkingReservationResponse;
import com.codeartify.examples.parking_spot_reservation.model.ParkingReservation;
import com.codeartify.examples.parking_spot_reservation.model.ParkingSpot;
import com.codeartify.examples.parking_spot_reservation.repository.ParkingReservationRepository;
import com.codeartify.examples.parking_spot_reservation.repository.ParkingSpotRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class ParkingReservationService {

    private static final LocalTime OPENING_TIME = LocalTime.of(6, 0); // 6:00 AM
    private static final LocalTime CLOSING_TIME = LocalTime.of(22, 0); // 10:00 PM
    
    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingReservationRepository parkingReservationRepository;

    @Transactional
    public ResponseEntity<Object> reserveParkingSpot(ParkingReservationRequest request) {
        if (Duration.between(request.getStartTime(), request.getEndTime()).toMinutes() < 30) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Reservation must be at least 30 minutes long.");
        }

        if (request.getEndTime().isBefore(request.getStartTime())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("End time must be after start time.");
        }

        if (request.getStartTime().toLocalTime().isBefore(OPENING_TIME) || request.getEndTime().toLocalTime().isAfter(CLOSING_TIME)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Reservations can only be made between 6:00 AM and 10:00 PM.");
        }
        
        final var hasActiveReservation = parkingReservationRepository.hasActiveReservation(request.getReservedBy(), request.getStartTime(), request.getEndTime());
        if (hasActiveReservation) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("You already have an active reservation.");
        }
        
        final var spot = parkingSpotRepository.findAnyAvailableSpot();
        if (spot == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No available spot left.");
        }
        
        final var reservation = new ParkingReservation(
                request.getReservedBy(),
                spot.getId(),
                request.getStartTime(),
                request.getEndTime());
        parkingReservationRepository.save(reservation);

        spot.setAvailable(false);
        parkingSpotRepository.save(spot);

        final var response = new ParkingReservationResponse();
        response.setReservationId(reservation.getId());
        response.setReservedBy(request.getReservedBy());
        response.setStartTime(request.getStartTime());
        response.setEndTime(request.getEndTime());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

