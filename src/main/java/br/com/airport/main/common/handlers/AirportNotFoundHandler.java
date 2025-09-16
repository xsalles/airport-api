package br.com.airport.main.common.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.airport.main.common.dto.ApiResponseDto;
import br.com.airport.main.common.exceptions.AirportNotFoundException;

@RestControllerAdvice
public class AirportNotFoundHandler {

    @ExceptionHandler(AirportNotFoundException.class)
    public ResponseEntity<ApiResponseDto<String>> handleAirportNotFoundException(AirportNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseDto<String>("Airport not found", HttpStatus.NOT_FOUND.value(), null));
    }
}
