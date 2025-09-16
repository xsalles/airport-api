package br.com.airport.main.common.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.airport.main.common.dto.ApiResponseDto;
import br.com.airport.main.common.exceptions.AirportAlreadyExistsException;

@RestControllerAdvice
public class AirportAlreadyExistsHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponseDto<String>> handleAirportAlreadyExistsException(
            AirportAlreadyExistsException ex) {
        return ResponseEntity.status(409)
                .body(new ApiResponseDto<String>(ex.getMessage(), 409, null));
    }
}
