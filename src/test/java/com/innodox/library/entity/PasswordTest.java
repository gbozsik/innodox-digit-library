package com.innodox.library.entity;

import com.innodox.library.service.implementation.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class PasswordTest {

     private static final Logger logger = Logger.getLogger(PasswordTest.class.getName());

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    Password password;

    @Before
    public void setUp() {
        password = new Password();
    }

    @Test
    public void getPassword() {
        String passwordValue = "password";
        password.setPassword(passwordValue);
        logger.info(password.getPassword());
        assertTrue(PASSWORD_ENCODER.matches(passwordValue, password.getPassword()));
    }

    @Test
    public void getId() {
        Long idValue = 45L;
        password.setId(idValue);
        assertEquals(idValue, password.getId());
    }
}