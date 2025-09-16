package br.com.airport.main.modules.Airport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.airport.main.common.dto.ApiResponseDto;
import br.com.airport.main.modules.Airport.model.AirportModel;
import br.com.airport.main.modules.Airport.service.AirportService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/airports")
public class AirportController {
    @Autowired
    private AirportService airportService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<AirportModel>> createAirport(@Valid @RequestBody AirportModel airportModel) {
        return airportService.createAirport(airportModel);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponseDto<List<AirportModel>>> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<AirportModel>> getAirportById(@PathVariable Integer id) {
        return airportService.getAirportsById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<AirportModel>> deleteAirportById(@PathVariable Integer id) {
        return airportService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<AirportModel>> updateAirport(@PathVariable Integer id,
            @Valid @RequestBody AirportModel airportModel) {
        return airportService.updateAirport(airportModel, id);
    }
}
