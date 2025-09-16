package br.com.airport.main.modules.Airport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.airport.main.common.dto.ApiResponseDto;
import br.com.airport.main.common.exceptions.AirportAlreadyExistsException;
import br.com.airport.main.modules.Airport.model.AirportModel;
import br.com.airport.main.modules.Airport.repository.AirportRepository;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    public ResponseEntity<ApiResponseDto<AirportModel>> createAirport(AirportModel airportModel) {
        if (airportRepository.existsByIATAOrName(airportModel.getIATA(), airportModel.getName())) {
            throw new AirportAlreadyExistsException("This airport already exists in our system.");
        }

        airportRepository.save(airportModel);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseDto<AirportModel>("Airport created successfully", HttpStatus.CREATED.value(), airportModel));
    }
}
