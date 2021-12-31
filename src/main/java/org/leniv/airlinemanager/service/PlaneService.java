package org.leniv.airlinemanager.service;

import org.leniv.airlinemanager.entity.Plane;

import java.util.List;
import java.util.Optional;

public interface PlaneService {

    List<Plane> findAll();

    List<Plane> findAll(
            Optional<String> name, Optional<Integer> rangeFrom, Optional<Integer> rangeTo,
            Optional<Integer> numberOfSeatsFrom, Optional<Integer> numberOfSeatsTo, Optional<Integer> fuelCapacityFrom,
            Optional<Integer> fuelCapacityTo, Optional<Integer> speedFrom, Optional<Integer> speedTo,
            Optional<Integer> mtowFrom, Optional<Integer> mtowTo, Optional<Long> airline
    );

    Optional<Plane> findById(Long id);

    void save(Plane plane);

    void deleteById(Long id);

}
