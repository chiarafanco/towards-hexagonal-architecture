package com.codeartify.examples.parkingreservation.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ParkingReservation {

    private final ParkingSpot parkingSpot;
    private final ReservationPeriod reservationPeriod;
    private final ReservingMemberId reservingMemberId;
    
    public static ParkingReservation create(ParkingSpot parkingSpot,
                                            ReservationPeriod reservationPeriod,
                                            ReservingMemberId reservingMemberId) {
        final ParkingSpot reservedSpot = parkingSpot.reserve();
        return new ParkingReservation(reservedSpot, reservationPeriod, reservingMemberId);
    }
}
