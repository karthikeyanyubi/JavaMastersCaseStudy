package com.example.casestudy.service;


import com.example.casestudy.dao.UserRepository;
import com.example.casestudy.dto.UserResponseDto;
import com.example.casestudy.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImplementation implements  UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto signUp(UserAccount user) {


        // Encrypt the password with Bcrypt
        String loginIdLower = user.getLoginId().toLowerCase();
        Optional<UserAccount>fetchUser = userRepository.findByLoginId(loginIdLower);

        if(fetchUser.isPresent())
        {
            throw new RuntimeException("User already signed up");
        }
        else
        {
            UUID uuid = UUID.randomUUID();
            String encodedPassword = passwordEncoder.encode(user.getPassword());

            // Save the user to the database
            user.setPassword(encodedPassword);
            user.setToken(uuid);

            userRepository.save(user);

            // Create and return the response DTO
            UserResponseDto responseDto = new UserResponseDto();
            responseDto.setMessage("User created successfully");
            responseDto.setLoginId(user.getLoginId());
            responseDto.setToken(uuid.toString());

            return responseDto;
        }
    }
}
