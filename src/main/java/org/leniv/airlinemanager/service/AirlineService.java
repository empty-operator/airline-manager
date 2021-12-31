package org.leniv.airlinemanager.service;

import org.leniv.airlinemanager.entity.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineService {

    List<Airline> findAll();

    List<Airline> findAll(
            Optional<String> name, Optional<Long> totalMtowFrom, Optional<Long> totalMtowTo,
            Optional<Long> totalNumberOfSeatsFrom, Optional<Long> totalNumberOfSeatsTo
    );

    Optional<Airline> findById(Long id);

    void save(Airline airline);

    void deleteById(Long id);

}
