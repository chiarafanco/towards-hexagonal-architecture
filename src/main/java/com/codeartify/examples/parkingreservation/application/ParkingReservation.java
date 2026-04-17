package com.codeartify.examples.parkingreservation.application;

import lombok.Builder;
import lombok.NonNull;

import java.time.LocalDateTime;

@Builder
public record ParkingReservation(long spotId,
                                 @NonNull String reservedBy,
                                 @NonNull LocalDateTime startTime,
                                 @NonNull LocalDateTime endTime) {
}