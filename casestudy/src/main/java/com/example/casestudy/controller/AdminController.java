package com.example.casestudy.controller;

import com.example.casestudy.dto.UserResponseDto;

import com.example.casestudy.model.UserAccount;
import com.example.casestudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/updateRole")
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

    @GetMapping("/getUsers")
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
