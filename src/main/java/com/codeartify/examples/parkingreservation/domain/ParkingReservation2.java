package com.codeartify.examples.parkingreservation.domain;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record ParkingReservation2(ParkingSpotId spotId,
                                  @NonNull String reservedBy,
                                  @NonNull ReservationPeriod period) {
}