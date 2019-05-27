package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;

public interface UserService {

    User register(User user) throws UserAlreadyExistsException;
    User login(String userName, String password) throws UserNotFoundException;
}
