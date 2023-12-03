package com.example.casestudy.controller;

import com.example.casestudy.dto.UserResponseDto;

import com.example.casestudy.model.UserAccount;
import com.example.casestudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<UserResponseDto> loginUser(@RequestBody UserAccount user) {
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
}
