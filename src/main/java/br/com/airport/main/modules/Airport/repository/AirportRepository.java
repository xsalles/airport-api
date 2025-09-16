package br.com.airport.main.modules.Airport.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.airport.main.modules.Airport.model.AirportModel;

public interface AirportRepository extends JpaRepository<AirportModel, Integer> {
   boolean existsByIATAOrName(String IATA, String name);
}
