package com.codeartify.examples.parkingreservation.application;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record ParkingReservation(long spotId,
                                 @NonNull String reservedBy,
                                 @NonNull ReservationPeriod reservationPeriod) {
}