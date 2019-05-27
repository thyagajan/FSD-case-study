package com.stackroute.userservice.controller;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.service.SecurityTokenGenerator;
import com.stackroute.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/userservice")
public class UserController {
    private ResponseEntity responseEntity;
    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        try{
            User userObj = userService.register(user);
            responseEntity = new ResponseEntity(userObj, HttpStatus.CREATED);
        }catch(UserAlreadyExistsException uaee){
            throw uaee;
        }catch(Exception e){
            responseEntity =  new ResponseEntity("Try after some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) throws UserNotFoundException {
        Map<String,String> map = null;
        try{
            User userObj = userService.login(user.getUserName(),user.getPassword());
            if(userObj.getUserName().equals(user.getUserName())){
                map = securityTokenGenerator.generateToken(user);
            }
            responseEntity =  new ResponseEntity(map, HttpStatus.OK);

        }catch(UserNotFoundException unfe){
            throw unfe;
        }catch(Exception e){
            responseEntity =  new ResponseEntity("Try after some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
