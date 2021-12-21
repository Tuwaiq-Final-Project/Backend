package com.example.barbertime.Repositories;

import com.example.barbertime.Entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {



//    @Autowired
//    private  UserRepository userRepository;


    // Note: To make the test work ( disable the validation )
//    @Test
//    void itShouldFindByEmail()
//    {
//        String email = "a@a.com";
            // Note: Add a constructor that have user & password at UserRepository
//        User user = new User(email,"123");
//        userRepository.save(user);
//
//        User result = userRepository.findByEmail(email);
//
//        assertEquals(email,result.getEmail());
//    }
}