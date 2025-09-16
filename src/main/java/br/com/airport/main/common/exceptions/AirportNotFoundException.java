package br.com.airport.main.common.exceptions;

public class AirportNotFoundException extends RuntimeException {
    public AirportNotFoundException(String message) {
        super(message);
    }
}
