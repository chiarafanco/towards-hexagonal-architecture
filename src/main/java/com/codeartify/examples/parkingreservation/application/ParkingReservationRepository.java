package com.codeartify.examples.parkingreservation.application;

import com.codeartify.examples.parkingreservation.domain.ParkingReservation;
import com.codeartify.examples.parkingreservation.domain.ReservationPeriod;

public interface ParkingReservationRepository {

    boolean existsOverlap(String reservedBy, ReservationPeriod reservationPeriod);
    long save(ParkingReservation reservation);
}
