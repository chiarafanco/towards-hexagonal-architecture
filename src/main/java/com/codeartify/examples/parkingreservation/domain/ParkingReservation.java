package com.codeartify.examples.parkingreservation.domain;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record ParkingReservation(long spotId,
                                 @NonNull String reservedBy,
                                 @NonNull ReservationPeriod reservationPeriod) {
}