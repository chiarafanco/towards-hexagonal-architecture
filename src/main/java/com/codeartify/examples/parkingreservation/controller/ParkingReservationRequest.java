package com.codeartify.examples.parkingreservation.controller;

import java.time.LocalDateTime;

record ParkingReservationRequest(String reservedBy,
                                 LocalDateTime startTime,
                                 LocalDateTime endTime) {
}
