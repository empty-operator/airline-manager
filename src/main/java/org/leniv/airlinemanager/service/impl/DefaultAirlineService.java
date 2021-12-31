package org.leniv.airlinemanager.service.impl;

import lombok.AllArgsConstructor;
import org.leniv.airlinemanager.entity.Airline;
import org.leniv.airlinemanager.repository.AirlineRepository;
import org.leniv.airlinemanager.service.AirlineService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultAirlineService implements AirlineService {

    private final AirlineRepository repo;

    @Override
    public List<Airline> findAll() {
        return repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<Airline> findAll(
            Optional<String> name, Optional<Long> totalMtowFrom, Optional<Long> totalMtowTo,
            Optional<Long> totalNumberOfSeatsFrom, Optional<Long> totalNumberOfSeatsTo
    ) {
        return repo.findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            name.ifPresent(o -> predicates.add(builder.like(builder.lower(root.get("name")), "%" + o.toLowerCase() +"%")));
            totalMtowFrom.ifPresent(o -> predicates.add(builder.ge(root.get("totalMtow"), o)));
            totalMtowTo.ifPresent(o -> predicates.add(builder.le(root.get("totalMtow"), o)));
            totalNumberOfSeatsFrom.ifPresent(o -> predicates.add(builder.ge(root.get("totalNumberOfSeats"), o)));
            totalNumberOfSeatsTo.ifPresent(o -> predicates.add(builder.le(root.get("totalNumberOfSeats"), o)));
            return builder.and(predicates.toArray(new Predicate[0]));
        }, Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Optional<Airline> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void save(Airline airline) {
        repo.save(airline);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

}
