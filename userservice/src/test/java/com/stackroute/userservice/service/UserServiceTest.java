package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    private User user;

    @InjectMocks
    private UserServiceImpl userService;

    Optional optional;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        user = new User("Thyagu","password","Thiyagarajan","Nagaraj");

    }

    @Test
    public void testRegisterUserSuccess(){
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User fetchUser = userRepository.save(user);
        Assert.assertEquals(user,fetchUser);
        verify(userRepository,times(1)).save(user);
    }

    @Test
    public void testUserLoginSuccess(){
        Mockito.when(userRepository.findByUserNameAndPassword(user.getUserName(),user.getPassword())).thenReturn(user);
        User fetchUser = userRepository.findByUserNameAndPassword(user.getUserName(),user.getPassword());
        Assert.assertEquals(user.getUserName(),fetchUser.getUserName());
        verify(userRepository,times(1)).findByUserNameAndPassword(user.getUserName(),user.getPassword());
    }

}
