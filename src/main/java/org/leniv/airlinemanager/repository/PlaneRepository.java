package org.leniv.airlinemanager.repository;

import org.leniv.airlinemanager.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Long>, JpaSpecificationExecutor<Plane> {
}