package com.example.test.task.service;

import com.example.test.task.domain.Strength;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class PasswordStrengthServiceImplTest {

    private PasswordStrengthServiceImpl service;

    @Before
    public void init(){
        service = new PasswordStrengthServiceImpl();
    }

    @Test
    public void testLowStrenght() throws Exception {
        assertEquals(service.checkStrenght("123"), Strength.LOW);
        assertEquals(service.checkStrenght("qwerty123"), Strength.LOW);
        assertEquals(service.checkStrenght("bla-bla-bla"), Strength.LOW);
    }

    @Test
    public void testMediunStrenght() throws Exception {
        assertEquals(service.checkStrenght("123-%aaa"), Strength.MEDIUM);
        assertEquals(service.checkStrenght("qWerty123"), Strength.MEDIUM);
        assertEquals(service.checkStrenght("Bla-Bla-Bla"), Strength.MEDIUM);
    }

    @Test
    public void testHightStrenght() throws Exception {
        assertEquals(service.checkStrenght("123-%aaaA"), Strength.HIGH);
        assertEquals(service.checkStrenght("qWerty123++"), Strength.HIGH);
        assertEquals(service.checkStrenght("Bla1-Bla2-Bla3"), Strength.HIGH);
    }
}