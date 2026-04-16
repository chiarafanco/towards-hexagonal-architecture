package com.codeartify.examples.parkingreservation.presentation;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
record ParkingReservationResponse(Long reservationId,
                                  String reservedBy,
                                  LocalDateTime startTime,
                                  LocalDateTime endTime) {
}
