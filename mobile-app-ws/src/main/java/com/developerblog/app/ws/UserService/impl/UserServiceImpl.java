package com.developerblog.app.ws.UserService.impl;

import java.util.HashMap;
import java.util.Map;
// import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developerblog.app.ws.UserService.UserService;
import com.developerblog.app.ws.shared.Utils;
import com.developerblog.app.ws.uiModelRequest.UserDetailsRequestModel;
import com.developerblog.app.ws.uiModelResponse.UserRest;

@Service
public class UserServiceImpl implements UserService {
    Map<String, UserRest> users;

    //IMPLEMENTING A CLASS BASE DEPENDENCY INJECTION

    Utils utils;

    public UserServiceImpl() {

    }
    
    @Autowired
    public UserServiceImpl(Utils utils) {
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {

        // THIS CODE IS MOVED FROM THE UserController.java

        UserRest returnValue = new UserRest();
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());
        returnValue.setEmail(userDetails.getEmail());

        // String userId = UUID.randomUUID().toString();
        String userId = utils.generateUserId();
        returnValue.setUserId(userId);

        if (users == null)
            users = new HashMap<>();
        users.put(userId, returnValue);

        return returnValue;
    }
    
}
