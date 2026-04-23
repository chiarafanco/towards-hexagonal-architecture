package com.codeartify.examples.parkingreservation.application;

public interface ParkingReservationRepository {

    boolean existsOverlap(String reservedBy, ReservationPeriod reservationPeriod);
    long save(ParkingReservation reservation);
}
