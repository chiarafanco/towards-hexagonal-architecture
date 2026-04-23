package com.codeartify.examples.parkingreservation.persistence;

import com.codeartify.examples.parkingreservation.domain.ParkingReservation2;
import com.codeartify.examples.parkingreservation.application.ParkingReservationRepository;
import com.codeartify.examples.parkingreservation.domain.ReservationPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ParkingReservationRepositoryAdapter implements ParkingReservationRepository {

    private final ParkingSpotJpaRepository spotJpaRepository;
    private final ParkingReservationJpaRepository reservationJpaRepository;

    @Override
    public boolean existsOverlap(String reservedBy, ReservationPeriod reservationPeriod) {
        return reservationJpaRepository.existsOverlap(reservedBy, reservationPeriod.startTime(), reservationPeriod.endTime());
    }
    
    @Override
    public long save(ParkingReservation2 reservation) {
        final var spotEntity = new ParkingSpotEntity();
        spotEntity.setId(reservation.spotId().value());
        spotEntity.setAvailable(false);
        spotJpaRepository.save(spotEntity);

        final var reservationEntity = new ParkingReservationEntity();
        reservationEntity.setSpotId(reservation.spotId().value());
        reservationEntity.setReservedBy(reservation.reservedBy());
        reservationEntity.setStartTime(reservation.period().startTime());
        reservationEntity.setEndTime(reservation.period().endTime());
        return reservationJpaRepository.save(reservationEntity).getId();
    }
}
