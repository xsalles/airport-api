package br.com.airport.main.modules.Airport.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AirportModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Pattern(regexp = "[a-zA-Z ]+", message = "Name must contain only letters and spaces")
    private String name;

    @Length(min = 3, max = 3)
    @Pattern(regexp = "^[A-Z]{3}$", message = "IATA code must be exactly 3 uppercase letters")
    private String IATA;

    @Pattern(regexp = "[a-zA-Z ]+", message = "City must contain only letters and spaces")
    private String city;
    
    @Pattern(regexp = "[a-zA-Z ]+", message = "State must contain only letters and spaces")
    private String state;

    @Pattern(regexp = "[a-zA-Z ]+", message = "Country must contain only letters and spaces")
    private String country;
}
