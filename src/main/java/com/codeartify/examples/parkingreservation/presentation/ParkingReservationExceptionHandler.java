package com.codeartify.examples.parkingreservation.presentation;

import com.codeartify.examples.parkingreservation.application.ParkingReservationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ParkingReservationExceptionHandler {
    
    @ExceptionHandler
    ProblemDetail handleException(ParkingReservationException.Overlapping e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler
    ProblemDetail handleException(ParkingReservationException.SpotUnavailable e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler
    ProblemDetail handleException(ParkingReservationException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}