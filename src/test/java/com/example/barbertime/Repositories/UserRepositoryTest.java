package com.example.barbertime.Repositories;

import com.example.barbertime.Entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {



    @Autowired
    private  UserRepository userRepository;


    @Test
    void itShouldFindByEmail()
    {
        String email = "a@a.com";
        User user = new User("saad","a@a.com","grntjntoug","05578555555");
        userRepository.save(user);

        User result = userRepository.findByEmail(email);
        assertEquals(email,result.getEmail());
    }
}