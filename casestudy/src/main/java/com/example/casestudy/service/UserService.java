package com.example.casestudy.service;

import com.example.casestudy.dto.UserResponseDto;

import com.example.casestudy.model.UserAccount;

import java.util.List;

public interface UserService {


    public UserResponseDto signUp(UserAccount user);

    public UserResponseDto login(UserAccount user);

    public UserResponseDto updateRole(UserAccount user);

    public List<UserResponseDto> getAllUsers();

    public UserAccount validateToken (String token);
}
