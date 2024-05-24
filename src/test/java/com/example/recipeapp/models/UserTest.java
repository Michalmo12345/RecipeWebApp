package com.example.recipeapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("JohnDoe");
        user.setEmail("john.doe@example.com");
        user.setPassword("securepassword");
    }

    @Test
    void getUsername() {
        assertEquals("JohnDoe", user.getUsername());
    }

    @Test
    void setUsername() {
        user.setUsername("JaneDoe");
        assertEquals("JaneDoe", user.getUsername());
    }

    @Test
    void getEmail() {
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    void setEmail() {
        user.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", user.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("securepassword", user.getPassword());
    }

    @Test
    void setPassword() {
        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());
    }
}