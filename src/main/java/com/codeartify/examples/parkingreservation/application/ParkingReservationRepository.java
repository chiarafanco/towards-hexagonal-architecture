package com.codeartify.examples.parkingreservation.application;

import java.time.LocalDateTime;

public interface ParkingReservationRepository {

    boolean existsOverlap(String reservedBy, LocalDateTime startTime, LocalDateTime endTime);
    
    long save(ParkingReservation reservation);
}
