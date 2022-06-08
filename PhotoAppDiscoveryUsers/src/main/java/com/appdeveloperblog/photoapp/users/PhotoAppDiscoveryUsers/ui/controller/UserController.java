package com.appdeveloperblog.photoapp.users.PhotoAppDiscoveryUsers.ui.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appdeveloperblog.photoapp.users.PhotoAppDiscoveryUsers.servise.UsersService;
import com.appdeveloperblog.photoapp.users.PhotoAppDiscoveryUsers.shared.UserDto;
import com.appdeveloperblog.photoapp.users.PhotoAppDiscoveryUsers.ui.model.CreateUserRequestModel;
import com.appdeveloperblog.photoapp.users.PhotoAppDiscoveryUsers.ui.model.CreateUserResponseModel;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private Environment env;

    @Autowired
    UsersService usersService;

    @GetMapping("/status/check")
    public String status() {
        return "User Microservice is Working" + " " + env.getProperty("local.server.port");
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userData) {
        
        // Create an instance of ModelMapper to map/copy the data from the request body
        // to the UserDto object
        ModelMapper modelMapper = new ModelMapper();

        // Match the properties of the source and destination objects Exactly as it is
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        // use ModelMapper to map/copy the request body to UserDto class
        UserDto userDto = modelMapper.map(userData, UserDto.class);
        UserDto createUser = usersService.createUser(userDto);
        CreateUserResponseModel returnValue = modelMapper.map(createUser, CreateUserResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

}
