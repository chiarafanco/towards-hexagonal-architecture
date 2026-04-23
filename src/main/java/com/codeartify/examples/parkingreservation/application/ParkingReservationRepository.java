package com.codeartify.examples.parkingreservation.application;

import com.codeartify.examples.parkingreservation.domain.ParkingReservation;
import com.codeartify.examples.parkingreservation.domain.ReservationPeriod;
import com.codeartify.examples.parkingreservation.domain.ReserverId;

public interface ParkingReservationRepository {

    long save(ParkingReservation reservation);
    boolean existsOverlap(ReserverId reserverId, ReservationPeriod reservationPeriod);
}
