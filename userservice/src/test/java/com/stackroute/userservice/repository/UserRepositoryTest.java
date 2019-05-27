package com.stackroute.userservice.repository;

import com.stackroute.userservice.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void setUp(){
        user = new User("userName","password","firstName","lastName");

    }

    @After
    public void tearDown(){
        userRepository.deleteAll();
        user = null;
    }

    @Test
    public void testRegisterSuccess(){
        userRepository.save(user);
        User userObj = userRepository.findByUserName(user.getUserName());
        Assert.assertEquals(userObj.getUserName(),user.getUserName());
        userRepository.delete(user);
    }

    @Test
    public void testUserLoginSuccess(){
        userRepository.save(user);
        User userObj = userRepository.findByUserNameAndPassword(user.getUserName(),user.getPassword());
        Assert.assertEquals(userObj.getUserName(),user.getUserName());
        userRepository.delete(user);
    }
}
