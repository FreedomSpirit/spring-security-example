package com.example.test.task.security;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PepperBCryptPasswordEncoderTest {

    private PepperBCryptPasswordEncoder encoder;

    @Before
    public void setUp() throws Exception {
        encoder = new PepperBCryptPasswordEncoder();
    }

    @Test
    public void testNotMatches() throws Exception {
        assertFalse(encoder.matches("WrongPassword", "$2a$10$N1LJ2tk4dW5Fc.fU6sq9duyWA.Aq0vvN9iDfSICLhNH.w1yJUnuW6"));
    }

    @Test
    public void testMatches() throws Exception {
        assertTrue(encoder.matches("test","$2a$10$N1LJ2tk4dW5Fc.fU6sq9duyWA.Aq0vvN9iDfSICLhNH.w1yJUnuW6"));
    }
}