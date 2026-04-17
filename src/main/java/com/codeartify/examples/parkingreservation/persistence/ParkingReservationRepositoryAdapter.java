package com.codeartify.examples.parkingreservation.persistence;

import com.codeartify.examples.parkingreservation.application.ParkingReservation;
import com.codeartify.examples.parkingreservation.application.ParkingReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
class ParkingReservationRepositoryAdapter implements ParkingReservationRepository {

    private final ParkingSpotJpaRepository spotJpaRepository;
    private final ParkingReservationJpaRepository reservationJpaRepository;
    
    @Override
    public boolean existsOverlap(String reservedBy, LocalDateTime startTime, LocalDateTime endTime) {
        return reservationJpaRepository.existsOverlap(reservedBy, startTime, endTime);
    }
    
    @Override
    public long save(ParkingReservation reservation) {
        final var spotEntity = new ParkingSpotEntity();
        spotEntity.setId(reservation.spotId());
        spotEntity.setAvailable(false);
        spotJpaRepository.save(spotEntity);

        final var reservationEntity = new ParkingReservationEntity();
        reservationEntity.setSpotId(reservation.spotId());
        reservationEntity.setReservedBy(reservation.reservedBy());
        reservationEntity.setStartTime(reservation.startTime());
        reservationEntity.setEndTime(reservation.endTime());
        return reservationJpaRepository.save(reservationEntity).getId();
    }
}
