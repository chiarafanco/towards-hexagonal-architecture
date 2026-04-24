package com.codeartify.examples.parkingreservation.domain;

import lombok.experimental.StandardException;

/**
 * Domain Exception: Errors that I could handle using the Result Object pattern.
 */
@StandardException
public abstract sealed class ParkingReservationException extends RuntimeException {

    public static final class DurationTooShort extends ParkingReservationException {

        public DurationTooShort() {
            super("Reservation must be at least 30 minutes long");
        }
    }
    
    public static final class EndBeforeStart extends ParkingReservationException {

        public EndBeforeStart() {
            super("Reservation end time must be after start time");
        }
    }

    public static final class OutsideOperatingHours extends ParkingReservationException {

        public OutsideOperatingHours() {
            super("Reservation can only be made between 6:00 AM and 10:00 PM");
        }
    }
    
    public static final class Overlapping extends ParkingReservationException {

        public Overlapping() {
            super("Reservation overlaps with an existing one");
        }
    }
    
    public static final class SpotUnavailable extends ParkingReservationException {
        
        public SpotUnavailable() {
            super("No available spot left");
        }
    }
}
