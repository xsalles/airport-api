package br.com.airport.main.modules.Airport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.airport.main.common.dto.ApiResponseDto;
import br.com.airport.main.modules.Airport.model.AirportModel;
import br.com.airport.main.modules.Airport.service.AirportService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class AirportController {
    @Autowired
    private AirportService airportService;

    @PostMapping("/airports/create")
    public ResponseEntity<ApiResponseDto<AirportModel>> createAirport(@Valid @RequestBody AirportModel airportModel) {
        return airportService.createAirport(airportModel);
    }

}
