package com.codeartify.examples.parkingreservation.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ReservationPeriod(LocalDateTime startTime, LocalDateTime endTime) {

    private static final Duration MINIMUM_DURATION = Duration.ofMinutes(30);
    private static final LocalTime OPENING_TIME = LocalTime.of(6, 0);
    private static final LocalTime CLOSING_TIME = LocalTime.of(22, 0);
    
    public static ReservationPeriod of(LocalDateTime startTime, LocalDateTime endTime) {
        ensureEndAfterStart(startTime, endTime);
        ensureMinimumDuration(startTime, endTime);
        ensureWithinOperatingHours(startTime, endTime);
        return new ReservationPeriod(startTime, endTime);
    }

    private static void ensureEndAfterStart(LocalDateTime startTime, LocalDateTime endTime) {
        if (endTime.isBefore(startTime)) {
            throw new ParkingReservationException.EndBeforeStart();
        }
    }

    private static void ensureMinimumDuration(LocalDateTime startTime, LocalDateTime endTime) {
        if (Duration.between(startTime, endTime).compareTo(MINIMUM_DURATION) < 0) {
            throw new ParkingReservationException.DurationTooShort();
        }
    }

    private static void ensureWithinOperatingHours(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.toLocalTime().isBefore(OPENING_TIME) || endTime.toLocalTime().isAfter(CLOSING_TIME)) {
            throw new ParkingReservationException.OutsideOperatingHours();
        }
    }
}
