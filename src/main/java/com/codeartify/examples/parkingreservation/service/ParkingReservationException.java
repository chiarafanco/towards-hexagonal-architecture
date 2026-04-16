package com.codeartify.examples.parkingreservation.service;

import lombok.experimental.StandardException;

@StandardException
public abstract sealed class ParkingReservationException extends RuntimeException {

    public static final class AlreadyExistsException extends ParkingReservationException {
        
        public AlreadyExistsException() {
            super("An active reservation already exists");
        }
    }

    public static final class PeriodEndBeforeStartException extends ParkingReservationException {
        
        public PeriodEndBeforeStartException() {
            super("End time must be after start time");
        }
    }

    public static final class PeriodOutsideOperatingHoursException extends ParkingReservationException {
        
        public PeriodOutsideOperatingHoursException() {
            super("Reservation can only be made between 6:00 AM and 10:00 PM");
        }
    }

    public static final class PeriodTooShortException extends ParkingReservationException {
        
        public PeriodTooShortException() {
            super("Reservation must be at least 30 minutes long");
        }
    }
    
    public static final class SpotUnavailableException extends ParkingReservationException {
        
        public SpotUnavailableException() {
            super("No available spot left");
        }
    }
}
