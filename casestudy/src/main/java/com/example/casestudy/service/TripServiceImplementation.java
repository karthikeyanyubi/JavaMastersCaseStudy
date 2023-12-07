package com.example.casestudy.service;


import com.example.casestudy.constants.enums.BusType;
import com.example.casestudy.dao.BusRepository;
import com.example.casestudy.dao.StaticRouteRepository;
import com.example.casestudy.dao.TripRepository;
import com.example.casestudy.model.Bus;
import com.example.casestudy.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TripServiceImplementation implements TripService    {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private StaticRouteRepository staticRouteRepository;


    @Override
    public void addTrip(Bus bus, Integer routeId, LocalTime startTime, LocalTime endTime) {
        Bus existingBus = busRepository.findById(bus.getBusId()).orElse(null);
        if (existingBus == null) {
            // Handle the case where the Bus does not exist
            throw new RuntimeException("Bus not found for the provided bus Id");
        }

        if (!staticRouteRepository.routeExists(routeId)) {
            // Handle the case where the route does not exist
            throw new RuntimeException("Route not found for the provided route Id");
        }

        // Step 2: Query existing trips for the bus
        List<Trip> existingTrips = tripRepository.findByBus(bus);

        // Step 3: Check for overlapping time slots
        for (Trip existingTrip : existingTrips) {
            if (isTimeOverlap(existingTrip.getStartTime(), existingTrip.getEndTime(), startTime, endTime)) {
                // Handle the case where there is an overlap
                throw new RuntimeException("Trip time overlaps with an existing trip");
            }
        }

        // Step 4: Create and save the new trip
        Trip newTrip = new Trip();
        newTrip.setBus(existingBus);
        newTrip.setRouteId(routeId);
        newTrip.setStartTime(startTime);
        newTrip.setEndTime(endTime);
        tripRepository.save(newTrip);
    }

    @Override
    public HashMap<String, Object> getBusesForRoute(Integer routeId) {
        HashMap<String, Object> response = new HashMap<>();
        if (!staticRouteRepository.routeExists(routeId)) {
            // Handle the case where the route does not exist
            throw new RuntimeException("Route not found for the provided route Id");
        }

        // Step 2: Query the buses for the route
        List<Object[]> resultList = tripRepository.getBusesForRoute(routeId);

        List<Bus> buses = new ArrayList<>();
        for (Object[] row : resultList) {
            Bus bus = new Bus();
            bus.setBusId((Integer) row[0]);
            bus.setBusType(BusType.valueOf((String) row[1]));
            bus.setRegNumber((String) row[2]);
            buses.add(bus);
        }
        // Step 3: Add the buses to the response
        response.put("buses", buses);

        // Step 4: Add the route to the response
        response.put("route", staticRouteRepository.getRoute(routeId));

        return response;
    }

    // Helper method to check for time overlap
    private boolean isTimeOverlap(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
        return !start1.isAfter(end2) && !end1.isBefore(start2);
    }

}
