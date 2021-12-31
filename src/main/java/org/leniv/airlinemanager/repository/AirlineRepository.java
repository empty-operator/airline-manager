package org.leniv.airlinemanager.repository;

import org.leniv.airlinemanager.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long>, JpaSpecificationExecutor<Airline> {
}