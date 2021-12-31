package org.leniv.airlinemanager.service.impl;

import lombok.AllArgsConstructor;
import org.leniv.airlinemanager.entity.Plane;
import org.leniv.airlinemanager.repository.PlaneRepository;
import org.leniv.airlinemanager.service.PlaneService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultPlaneService implements PlaneService {

    private final PlaneRepository repo;

    @Override
    public List<Plane> findAll() {
        return repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<Plane> findAll(
            Optional<String> name, Optional<Integer> rangeFrom, Optional<Integer> rangeTo,
            Optional<Integer> numberOfSeatsFrom, Optional<Integer> numberOfSeatsTo, Optional<Integer> fuelCapacityFrom,
            Optional<Integer> fuelCapacityTo, Optional<Integer> speedFrom, Optional<Integer> speedTo,
            Optional<Integer> mtowFrom, Optional<Integer> mtowTo, Optional<Long> airline
    ) {
        return repo.findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            name.ifPresent(o -> predicates.add(builder.like(builder.lower(root.get("name")), "%" + o.toLowerCase() +"%")));
            rangeFrom.ifPresent(o -> predicates.add(builder.ge(root.get("range"), o)));
            rangeTo.ifPresent(o -> predicates.add(builder.le(root.get("range"), o)));
            numberOfSeatsFrom.ifPresent(o -> predicates.add(builder.ge(root.get("numberOfSeats"), o)));
            numberOfSeatsTo.ifPresent(o -> predicates.add(builder.le(root.get("numberOfSeats"), o)));
            fuelCapacityFrom.ifPresent(o -> predicates.add(builder.ge(root.get("fuelCapacity"), o)));
            fuelCapacityTo.ifPresent(o -> predicates.add(builder.le(root.get("fuelCapacity"), o)));
            speedFrom.ifPresent(o -> predicates.add(builder.ge(root.get("speed"), o)));
            speedTo.ifPresent(o -> predicates.add(builder.le(root.get("speed"), o)));
            mtowFrom.ifPresent(o -> predicates.add(builder.ge(root.get("mtow"), o)));
            mtowTo.ifPresent(o -> predicates.add(builder.le(root.get("mtow"), o)));
            airline.ifPresent(o -> predicates.add(builder.equal(root.get("airline").get("id"), o)));
            return builder.and(predicates.toArray(new Predicate[0]));
        }, Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Optional<Plane> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void save(Plane plane) {
        repo.save(plane);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

}
