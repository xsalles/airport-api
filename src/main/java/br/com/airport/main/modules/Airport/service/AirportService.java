package br.com.airport.main.modules.Airport.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.airport.main.common.dto.ApiResponseDto;
import br.com.airport.main.common.exceptions.AirportAlreadyExistsException;
import br.com.airport.main.common.exceptions.AirportNotFoundException;
import br.com.airport.main.modules.Airport.model.AirportModel;
import br.com.airport.main.modules.Airport.repository.AirportRepository;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    public ResponseEntity<ApiResponseDto<AirportModel>> createAirport(AirportModel airportModel) {
        if (airportRepository.existsByIataOrName(airportModel.getIata(), airportModel.getName())) {
            throw new AirportAlreadyExistsException("This airport already exists in our system.");
        }

        airportRepository.save(airportModel);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseDto<AirportModel>("Airport created successfully", HttpStatus.CREATED.value(),
                        airportModel));
    }

    public ResponseEntity<ApiResponseDto<List<AirportModel>>> getAllAirports() {
        if (airportRepository.findAll().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseDto<List<AirportModel>>("Airports retrieved successfully", HttpStatus.OK.value(),
                        airportRepository.findAll()));
    }

    public ResponseEntity<ApiResponseDto<AirportModel>> getAirportsById(Integer id) {
        if (!airportRepository.findById(id).isPresent()) {
            throw new AirportNotFoundException("Airport not found.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseDto<AirportModel>("Airport retrieved successfully", HttpStatus.OK.value(),
                        airportRepository.findById(id).get()));
    }

    public ResponseEntity<ApiResponseDto<AirportModel>> deleteById(Integer id) {
        if (!airportRepository.findById(id).isPresent()) {
            throw new AirportNotFoundException("Airport not found.");
        }

        airportRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseDto<AirportModel>("Airport deleted successfully", HttpStatus.OK.value(), null));
    }

    public ResponseEntity<ApiResponseDto<AirportModel>> updateAirport(AirportModel airportModel, Integer id) {
        if (!airportRepository.existsById(id)) {
            throw new AirportNotFoundException("Airport not found.");
        }

        AirportModel existingAirport = airportRepository.findById(id).get();

        boolean alreadyExists = airportRepository.existsByIataOrName(airportModel.getIata(), airportModel.getName())
                && !Objects.equals(existingAirport.getIata(), airportModel.getIata())
                && !Objects.equals(existingAirport.getName(), airportModel.getName());

        if (alreadyExists) {
            throw new AirportAlreadyExistsException(
                    "One airport with these informations already exists in our system.");
        }

        existingAirport.setName(airportModel.getName());
        existingAirport.setIata(airportModel.getIata());
        existingAirport.setCity(airportModel.getCity());
        existingAirport.setState(airportModel.getState());
        existingAirport.setCountry(airportModel.getCountry());

        airportRepository.save(existingAirport);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseDto<AirportModel>("Airport updated successfully", HttpStatus.OK.value(),
                        airportModel));
    }
}
