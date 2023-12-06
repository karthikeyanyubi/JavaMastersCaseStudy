package com.example.casestudy.service;

import com.example.casestudy.model.Route;

import java.util.List;

public interface RouteService {

    public void addRoute(Route route);

    public List<Route> getAllRoutes();

    public void deleteRoute(int routeId);
}
