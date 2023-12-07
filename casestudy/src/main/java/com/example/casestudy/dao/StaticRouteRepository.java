package com.example.casestudy.dao;


import com.example.casestudy.model.Route;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StaticRouteRepository {

    Map<Integer, Route> staticRoute = new HashMap<>();

    public StaticRouteRepository()
    {
        Route route = new Route(1, "route1");
        staticRoute.put(route.getRouteId(), route);
        route = new Route(2, "route2");
        staticRoute.put(route.getRouteId(), route);
        route = new Route(3, "route3");
        staticRoute.put(route.getRouteId(), route);
    }

    public void addRoute(Route route)
    {
        String routeLower = route.getRouteName().toLowerCase().trim();
        route.setRouteName(routeLower);
        if(staticRoute.containsKey(route.getRouteId()))
            throw new RuntimeException("Route already exists for the provided Id");

        if (staticRoute.values().stream().anyMatch(existingRoute -> existingRoute.getRouteName().equals(route.getRouteName()))) {
            throw new RuntimeException("Route with the same name already exists");
        }
        staticRoute.put(route.getRouteId(), route);
    }

    public List<Route> getAllRoutes()
    {
        return new ArrayList<>(staticRoute.values());
    }

    public Route getRoute(int routeId)
    {
        if(!staticRoute.containsKey(routeId))
            throw new RuntimeException("Route does not exist for the provided Id");
        return staticRoute.get(routeId);
    }

    public void deleteRoute(int routeId)
    {
        if(!staticRoute.containsKey(routeId))
            throw new RuntimeException("Route does not exist for the provided Id");
        staticRoute.remove(routeId);
    }

    public boolean routeExists(int routeId)
    {
        return staticRoute.containsKey(routeId);
    }

}
