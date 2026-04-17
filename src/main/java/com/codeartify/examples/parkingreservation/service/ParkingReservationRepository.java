package com.codeartify.examples.parkingreservation.service;

import com.codeartify.examples.parkingreservation.model.ParkingReservation;

import java.time.LocalDateTime;

public interface ParkingReservationRepository {

    boolean existsOverlap(String reservedBy, LocalDateTime startTime, LocalDateTime endTime);
    
    long save(ParkingReservation reservation);
}
