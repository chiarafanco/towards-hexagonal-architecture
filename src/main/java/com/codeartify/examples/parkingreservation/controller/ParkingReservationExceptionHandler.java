package com.codeartify.examples.parkingreservation.controller;

import com.codeartify.examples.parkingreservation.service.ParkingReservationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ParkingReservationExceptionHandler {
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    String handleException(ParkingReservationException.AlreadyExistsException e) {
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleException(ParkingReservationException.SpotUnavailableException e) {
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleException(ParkingReservationException e) {
        return e.getMessage();
    }
}