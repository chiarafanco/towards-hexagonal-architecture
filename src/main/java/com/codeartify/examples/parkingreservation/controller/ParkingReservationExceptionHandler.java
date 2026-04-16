package com.codeartify.examples.parkingreservation.controller;

import com.codeartify.examples.parkingreservation.service.ParkingReservationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ParkingReservationExceptionHandler {
    
    @ExceptionHandler
    ProblemDetail handleException(ParkingReservationException.AlreadyExistsException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler
    ProblemDetail handleException(ParkingReservationException.SpotUnavailableException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler
    ProblemDetail handleException(ParkingReservationException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}