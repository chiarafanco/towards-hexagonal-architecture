package com.codeartify.examples.parkingreservation.domain;

import lombok.NonNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ReservationPeriod(LocalDateTime startTime,
                                LocalDateTime endTime) {

    private static final LocalTime OPENING_TIME = LocalTime.of(6, 0);
    private static final LocalTime CLOSING_TIME = LocalTime.of(22, 0);
    private static final Duration MINIMUM_DURATION = Duration.ofMinutes(30);
    
    public static ReservationPeriod of(@NonNull LocalDateTime startTime, @NonNull LocalDateTime endTime) {
        validateTimeOrder(startTime, endTime);
        validateOperatingHours(startTime, endTime);
        validateMinimumDuration(startTime, endTime);
        return new ReservationPeriod(startTime, endTime);
    }

    private static void validateTimeOrder(LocalDateTime startTime, LocalDateTime endTime) {
        if (endTime.isBefore(startTime)) {
            throw new ParkingReservationException.EndBeforeStart();
        }
    }

    private static void validateOperatingHours(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.toLocalTime().isBefore(OPENING_TIME) || endTime.toLocalTime().isAfter(CLOSING_TIME)) {
            throw new ParkingReservationException.OutsideOperatingHours();
        }
    }

    private static void validateMinimumDuration(LocalDateTime startTime, LocalDateTime endTime) {
        if (Duration.between(startTime, endTime).compareTo(MINIMUM_DURATION) < 0) {
            throw new ParkingReservationException.DurationTooShort();
        }
    }
}
