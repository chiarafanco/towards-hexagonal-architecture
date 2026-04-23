package com.codeartify.examples.parkingreservation.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ParkingReservation {

    private final ReserverId reserverId;
    private final ParkingSpot parkingSpot;
    private final ReservationPeriod reservationPeriod;
    
    public static ParkingReservation reserve(ReserverId reserverId,
                                             ParkingSpot parkingSpot,
                                             ReservationPeriod reservationPeriod) {
        final var reservedSpot = parkingSpot.reserve();
        return new ParkingReservation(reserverId, reservedSpot, reservationPeriod);
    }
}
