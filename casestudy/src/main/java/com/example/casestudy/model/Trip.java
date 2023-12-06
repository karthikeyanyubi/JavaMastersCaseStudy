package com.example.casestudy.model;


import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "BusRoutes")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer busRouteId;

    private Integer routeId;
    @ManyToOne
    @JoinColumn(name = "busId")
    private Bus bus;


    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    // getters and setters
}
