package com.example.casestudy.controller;

import com.example.casestudy.dto.UserResponseDto;

import com.example.casestudy.model.UserAccount;
import com.example.casestudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/allUsers/signUp")
    public ResponseEntity<UserResponseDto> signUpUser(@RequestBody UserAccount user) {
        try
        {
            UserResponseDto signedUpUser= userService.signUp(user);
            return ResponseEntity.status(HttpStatus.OK).body(signedUpUser);

        }
        catch (Exception e)
        {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(UserResponseDto.builder().message(e.getMessage()).build());
        }
    }

    @PostMapping("/allUsers/login")
    public ResponseEntity<UserResponseDto> login (@RequestBody UserAccount user) {
        try
        {
            UserResponseDto loggedInUser= userService.login(user);
            return ResponseEntity.status(HttpStatus.OK).body(loggedInUser);

        }
        catch (Exception e)
        {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(UserResponseDto.builder().message(e.getMessage()).build());
        }


    }

    @PostMapping("/admin/updateRole")
    public ResponseEntity<UserResponseDto> updateRole(@RequestBody UserAccount user) {
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

    @GetMapping("/admin/getUsers")
    public ResponseEntity<Object> getUsers() {
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

}
