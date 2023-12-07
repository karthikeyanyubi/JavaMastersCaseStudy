package com.example.casestudy.dao;

import com.example.casestudy.model.Bus;
import com.example.casestudy.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

    List<Trip> findByBus(Bus bus);


    //Stored procedure retrieval of buses for a route

    @Query(value = "SELECT * FROM get_buses_for_route(:routeId)", nativeQuery = true)
    List<Object[]> getBusesForRoute(@Param("routeId") int routeId);


}
