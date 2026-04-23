package com.codeartify.examples.parkingreservation.domain;

public record ReserverId(String value) {
    
    public static ReserverId of(String value) {
        return new ReserverId(value);
    }
}
