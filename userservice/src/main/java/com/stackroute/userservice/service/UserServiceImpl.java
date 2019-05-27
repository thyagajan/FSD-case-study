package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) throws UserAlreadyExistsException {
        User userObj = userRepository.findByUserName(user.getUserName());
        if(userObj != null){
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    @Override
    public User login(String userName, String password) throws UserNotFoundException {
        User user = userRepository.findByUserNameAndPassword(userName,password);
        if(user == null){
            throw new UserNotFoundException();
        }
        return user;
    }
}
