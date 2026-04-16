package com.codeartify.examples.parkingreservation.presentation;

import java.time.LocalDateTime;

record ParkingReservationRequest(String reservedBy,
                                 LocalDateTime startTime,
                                 LocalDateTime endTime) {
}
