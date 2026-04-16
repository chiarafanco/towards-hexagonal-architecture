package com.codeartify.examples.parkingreservation.infrastructure;

import com.codeartify.examples.parkingreservation.model.ParkingReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ParkingReservationRepository extends JpaRepository<ParkingReservation, Long> {

    @Query("""
            SELECT COUNT(r) > 0
            FROM ParkingReservation r
            WHERE r.reservedBy = :reservedBy
            AND r.startTime < :endTime
            AND r.endTime > :startTime
            """)
    boolean existsOverlapping(String reservedBy, LocalDateTime startTime, LocalDateTime endTime);
}
