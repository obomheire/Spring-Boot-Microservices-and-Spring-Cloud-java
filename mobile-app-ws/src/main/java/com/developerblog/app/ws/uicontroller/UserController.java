package com.developerblog.app.ws.uicontroller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.developerblog.app.ws.UserService.UserService;
// import com.developerblog.app.ws.exceptions.UserServiceException;
import com.developerblog.app.ws.uiModelRequest.UpdateUserDetailsRequestModel;
import com.developerblog.app.ws.uiModelRequest.UserDetailsRequestModel;
import com.developerblog.app.ws.uiModelResponse.UserRest;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    Map<String, UserRest> users;

    @Autowired
    UserService userService;

    @GetMapping
    public String getUsersPaginate(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "50") int limit,
            @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
        return "get user was called with page: " + page + " and limit: " + limit + " sort: " + sort;
    }

    // @GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE,
    // MediaType.APPLICATION_JSON_VALUE }) or as follows:
    @GetMapping(path = "/{userId}", produces = { "application/json", "application/xml" })
    public ResponseEntity<UserRest> getUsers(@PathVariable String userId) {

        //THIS IS A TEST CONDITION JUST TO TEST IS A EXCEPTION HANDLER IS WORKING.
        // if (true)
        //     throw new UserServiceException("A user service exception occurred");

        // Initilal
        // UserRest returnValue = new UserRest();
        // returnValue.setFirstName("Zack");
        // returnValue.setLastName("Bello");

        // returnValue.setEmail("xnotime247@gmail.com");
        // returnValue.setUserId(userId);

        // return new ResponseEntity<UserRest>(returnValue, HttpStatus.FOUND);

        // Updated
        if (users.containsKey(userId)) {
            return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
        }

    }

    // @GetMapping
    // public String getUsers() {
    // return "get user was called";
    // }

    // @PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE,
    // MediaType.APPLICATION_JSON_VALUE }, produces = {
    // MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }) or as
    // follows
    @PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
            "application/xml" })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) 
    {

        // THIS CODE IS MOVED TO THE UserServiceImpl.java

        // UserRest returnValue = new UserRest();
        // returnValue.setFirstName(userDetails.getFirstName());
        // returnValue.setLastName(userDetails.getLastName());
        // returnValue.setEmail(userDetails.getEmail());

        // String userId = UUID.randomUUID().toString();
        // returnValue.setUserId(userId);

        // if (users == null)
        // users = new HashMap<>();
        // users.put(userId, returnValue);
        UserRest returnValue = userService.createUser(userDetails);
        return new ResponseEntity<UserRest>(returnValue, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{userId}", consumes = { "application/json", "application/xml" }, produces = {
            "application/json", "application/xml" })
    public UserRest UpdateUser(@PathVariable String userId,
            @Valid @RequestBody UpdateUserDetailsRequestModel updateUserDetails) {
        UserRest storeUserDetails = users.get(userId);
        storeUserDetails.setFirstName(updateUserDetails.getFirstName());
        storeUserDetails.setLastName(updateUserDetails.getLastName());

        users.put(userId, storeUserDetails);
        return storeUserDetails;
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
         users.remove(userId);
         return ResponseEntity.noContent().build();
    }

}
