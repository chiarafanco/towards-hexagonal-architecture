package com.codeartify.examples.parking_spot_reservation.service;

public abstract sealed class ParkingReservationException extends RuntimeException {

    public static final class AlreadyExists extends ParkingReservationException {}

    public static final class PeriodEndBeforeStart extends ParkingReservationException {}

    public static final class PeriodOutsideOperatingHours extends ParkingReservationException {}

    public static final class PeriodTooShort extends ParkingReservationException {}
    
    public static final class SpotUnavailable extends ParkingReservationException {}
}
