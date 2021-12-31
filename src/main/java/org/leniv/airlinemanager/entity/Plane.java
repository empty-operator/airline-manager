package org.leniv.airlinemanager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer range;

    @Column
    private Integer numberOfSeats;

    @Column
    private Integer fuelCapacity;

    @Column
    private Integer speed;

    @Column
    private Integer mtow;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

}
