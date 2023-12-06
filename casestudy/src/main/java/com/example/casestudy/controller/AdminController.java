package com.example.casestudy.controller;

import com.example.casestudy.dto.EntityResponseDto;
import com.example.casestudy.dto.UserResponseDto;

import com.example.casestudy.model.Bus;
import com.example.casestudy.model.Route;
import com.example.casestudy.model.UserAccount;
import com.example.casestudy.service.BusService;
import com.example.casestudy.service.BusServiceImplementation;
import com.example.casestudy.service.RouteService;
import com.example.casestudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private BusService busService;

    @PostMapping("/updateRole")
    public ResponseEntity<UserResponseDto> updateRole(@RequestHeader(value = "auth_token")  String authToken, @RequestBody UserAccount user) {
        try
        {
            UserResponseDto updatedUser= userService.updateRole(user);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);

        }
        catch (Exception e)
        {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(UserResponseDto.builder().message(e.getMessage()).build());
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Object> getUsers(@RequestHeader(value = "auth_token")  String authToken) {
        try
        {
            List<UserResponseDto> users= userService.getAllUsers();
            return ResponseEntity.status(HttpStatus.OK).body(users);

        }
        catch (Exception e)
        {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(UserResponseDto.builder().message(e.getMessage()).build());
        }
    }


    @PostMapping("/deleteRoute/{routeId}")
    public ResponseEntity<EntityResponseDto> deleteRoute(@RequestHeader(value = "auth_token")  String authToken, @PathVariable("routeId") int routeId) {
        try
        {
            routeService.deleteRoute(routeId);
            return ResponseEntity.ok().body(EntityResponseDto.builder().message("Route deleted successfully").build());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(EntityResponseDto.builder().message(e.getMessage()).build());
        }
    }

    @PostMapping("/addRoute")
    public ResponseEntity<EntityResponseDto> addRoute(@RequestHeader(value = "auth_token")  String authToken,@Valid @RequestBody Route route) {
        try
        {
            routeService.addRoute(route);
            return ResponseEntity.ok().body(EntityResponseDto.builder().message("Route added successfully").build());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(EntityResponseDto.builder().message(e.getMessage()).build());
        }
    }

    @PostMapping("/addBusDetails")
    public ResponseEntity<EntityResponseDto> addBusDetails(@RequestHeader(value = "auth_token")  String authToken,@Valid @RequestBody Bus bus) {
        try
        {
            busService.addBus(bus);
            return ResponseEntity.ok().body(EntityResponseDto.builder().message("Bus details added successfully").build());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(EntityResponseDto.builder().message(e.getMessage()).build());
        }
    }
    @PostMapping("/modifyBus/{busId}")
    public ResponseEntity<EntityResponseDto> modifyBus(@RequestHeader(value = "auth_token")  String authToken, @PathVariable("busId") int busId,@Valid @RequestBody Bus bus) {
        try
        {
            busService.updateBus(busId,bus  );
            return ResponseEntity.ok().body(EntityResponseDto.builder().message("Bus modified successfully").build());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(EntityResponseDto.builder().message(e.getMessage()).build());
        }
    }

    @PostMapping("/deleteBus/{busId}")
    public ResponseEntity<EntityResponseDto> deleteBus(@RequestHeader(value = "auth_token")  String authToken, @PathVariable("busId") int busId) {
        try
        {
            busService.deleteBus(busId);
            return ResponseEntity.ok().body(EntityResponseDto.builder().message("Bus deleted successfully").build());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(EntityResponseDto.builder().message(e.getMessage()).build());
        }
    }

}
