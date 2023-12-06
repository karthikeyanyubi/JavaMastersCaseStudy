package com.example.casestudy.service;


import com.example.casestudy.dao.StaticRouteRepository;
import com.example.casestudy.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImplementation implements RouteService {

    @Autowired
    private StaticRouteRepository staticRouteRepository;


    @Override
    public void addRoute(Route route) {
        staticRouteRepository.addRoute(route);
    }

    @Override
    public List<Route> getAllRoutes() {
        return staticRouteRepository.getAllRoutes();
    }

    @Override
    public void deleteRoute(int routeId) {
        staticRouteRepository.deleteRoute(routeId);
    }
}
