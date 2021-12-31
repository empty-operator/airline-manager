package org.leniv.airlinemanager.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "airline")
    private List<Plane> planes;

    @Formula("(select sum(p.mtow) from plane p where p.airline_id = id)")
    private Long totalMtow;

    @Formula("(select sum(p.number_of_seats) from plane p where p.airline_id = id)")
    private Long totalNumberOfSeats;

}
