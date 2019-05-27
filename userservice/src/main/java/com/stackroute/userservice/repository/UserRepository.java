package com.stackroute.userservice.repository;

import com.stackroute.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserNameAndPassword(String userName, String Password);
    User findByUserName(String userName);
}
