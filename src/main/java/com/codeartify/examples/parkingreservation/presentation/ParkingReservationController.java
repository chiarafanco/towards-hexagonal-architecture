package com.codeartify.examples.parkingreservation.presentation;

import com.codeartify.examples.parkingreservation.application.ParkingReservationService;
import com.codeartify.examples.parkingreservation.application.ReservationPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class ParkingReservationController {

    private final ParkingReservationService parkingReservationService;

    @PostMapping("/reserve-spot")
    @ResponseStatus(HttpStatus.CREATED)
    ParkingReservationResponse reserveSpot(@RequestBody ParkingReservationRequest request) {
        final var reservationId = parkingReservationService.reserveSpot(
                request.reservedBy(),
                ReservationPeriod.of(request.startTime(), request.endTime()));
        
        return ParkingReservationResponse.builder()
                .reservationId(reservationId)
                .reservedBy(request.reservedBy())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .build();
    }
}
