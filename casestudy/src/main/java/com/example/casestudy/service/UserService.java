package com.example.casestudy.service;

import com.example.casestudy.dto.UserResponseDto;

import com.example.casestudy.model.UserAccount;

public interface UserService {


    public UserResponseDto signUp(UserAccount user);
}
