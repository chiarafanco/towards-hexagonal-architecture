package com.codeartify.examples.parkingreservation.application;

import java.time.LocalDateTime;

public record ReservationPeriod(LocalDateTime startTime,
                                LocalDateTime endTime) {
}
