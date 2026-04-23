package com.codeartify.examples.parkingreservation.persistence;

import com.codeartify.examples.parkingreservation.domain.ParkingReservation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ParkingReservationMapper {
    
    public static ParkingReservationEntity map(ParkingReservation reservation) {
        final var reservationEntity = new ParkingReservationEntity();
        reservationEntity.setSpotId(reservation.getParkingSpot().getId().value());
        reservationEntity.setReservedBy(reservation.getReserverId().value());
        reservationEntity.setStartTime(reservation.getReservationPeriod().startTime());
        reservationEntity.setEndTime(reservation.getReservationPeriod().endTime());
        return reservationEntity;
    }
}