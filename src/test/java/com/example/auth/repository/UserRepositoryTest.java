package com.example.auth.repository;

import com.example.auth.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername_success() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("encodedPassword");
        user.setRole("USER");
        userRepository.save(user);

        User found = userRepository.findByUsername("testuser");
        assertNotNull(found);
        assertEquals("testuser", found.getUsername());
    }

    @Test
    void findByUsername_notFound() {
        User found = userRepository.findByUsername("nonexistent");
        assertNull(found);
    }
}