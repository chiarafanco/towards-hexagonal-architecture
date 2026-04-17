package com.codeartify.examples.parkingreservation.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ParkingReservationJpaRepository extends JpaRepository<ParkingReservationEntity, Long> {

    @Query("""
            SELECT COUNT(r) > 0
            FROM ParkingReservation r
            WHERE r.reservedBy = :reservedBy
            AND r.startTime < :endTime
            AND r.endTime > :startTime
            """)
    boolean existsOverlap(String reservedBy, LocalDateTime startTime, LocalDateTime endTime);
}
