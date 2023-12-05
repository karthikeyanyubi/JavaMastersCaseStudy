package com.example.casestudy.service;


import com.example.casestudy.dao.UserRepository;
import com.example.casestudy.dto.UserResponseDto;
import com.example.casestudy.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

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

            String encodedPassword = passwordEncoder.encode(user.getPassword());

            // Save the user to the database
            user.setPassword(encodedPassword);

            userRepository.save(user);

            // Create and return the response DTO
            UserResponseDto responseDto = new UserResponseDto();
            responseDto.setMessage("User created successfully");

            return responseDto;
        }
    }

    @Override
    public UserResponseDto login(UserAccount user) {
        String loginIdLower = user.getLoginId().toLowerCase();
        Optional<UserAccount>fetchUser = userRepository.findByLoginId(loginIdLower);

        if(fetchUser.isEmpty())
        {
            throw new RuntimeException("User not found");
        }
        else {
            UserAccount userAccount = fetchUser.get();

            // Check if the password matches
            boolean isPasswordMatched = passwordEncoder.matches(user.getPassword(), userAccount.getPassword());

            if (isPasswordMatched) {
                // Generate a token
                UUID token = UUID.randomUUID();

                // Save the token to the database
                userAccount.setToken(token.toString());

                userRepository.save(userAccount);

                // Create and return the response DTO
                UserResponseDto responseDto = new UserResponseDto();
                responseDto.setMessage("User logged in successfully");
                responseDto.setLoginId(userAccount.getLoginId());
                responseDto.setToken(token.toString());

                return responseDto;
            } else {
                throw new RuntimeException("Password do not match");
            }
        }
    }

    @Override
    public UserResponseDto updateRole(UserAccount user) {
        String loginIdLower = user.getLoginId().toLowerCase();
        Optional<UserAccount>fetchUser = userRepository.findByLoginId(loginIdLower);
        if(fetchUser.isEmpty())
        {
            throw new RuntimeException("User not found for the provided login Id");
        }
        UserAccount fetchedUser = fetchUser.get();
        fetchedUser.setIsAdmin(user.getIsAdmin());
        userRepository.save(fetchedUser);

        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setMessage("User role updated successfully");
        responseDto.setIsAdmin(fetchedUser.getIsAdmin());

        return responseDto;

    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<UserAccount> users = userRepository.findAll();
        List<UserResponseDto> fetchedUsers = new ArrayList<>();
        for(UserAccount userAccount: users)
        {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setLoginId(userAccount.getLoginId());
            userResponseDto.setIsAdmin(userAccount.getIsAdmin());
            fetchedUsers.add(userResponseDto);
        }
        return fetchedUsers;
    }

    @Override
    public UserAccount validateToken(String token) {
        Optional<UserAccount>fetchUser = userRepository.findByToken(token);
        if(fetchUser.isEmpty())
        {
            return null;
        }
        else
        {
            return fetchUser.get();
        }
    }
}
