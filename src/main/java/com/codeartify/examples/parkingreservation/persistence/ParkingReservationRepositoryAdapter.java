package com.codeartify.examples.parkingreservation.persistence;

import com.codeartify.examples.parkingreservation.application.ParkingReservationRepository;
import com.codeartify.examples.parkingreservation.domain.ParkingReservation;
import com.codeartify.examples.parkingreservation.domain.ReservationPeriod;
import com.codeartify.examples.parkingreservation.domain.ReserverId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ParkingReservationRepositoryAdapter implements ParkingReservationRepository {

    private final ParkingSpotJpaRepository spotJpaRepository;
    private final ParkingReservationJpaRepository reservationJpaRepository;

    @Override
    public long save(ParkingReservation reservation) {
        final var spotEntity = ParkingSpotMapper.map(reservation.getParkingSpot());
        spotJpaRepository.save(spotEntity);

        final var reservationEntity = ParkingReservationMapper.map(reservation);
        return reservationJpaRepository.save(reservationEntity).getId();
    }

    @Override
    public boolean hasOverlap(ReserverId reserverId, ReservationPeriod reservationPeriod) {
        return reservationJpaRepository.existsOverlap(
                reserverId.value(),
                reservationPeriod.startTime(),
                reservationPeriod.endTime());
    }
}
