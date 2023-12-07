package com.example.casestudy.service;

import com.example.casestudy.model.Bus;

import java.time.LocalTime;
import java.util.HashMap;

public interface TripService {

    public void addTrip(Bus bus, Integer routeId, LocalTime startTime, LocalTime endTime);

    public HashMap<String, Object> getBusesForRoute(Integer routeId);
}
