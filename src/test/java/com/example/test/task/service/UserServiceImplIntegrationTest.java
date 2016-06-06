package com.example.test.task.service;

import com.example.test.task.TestTaskApplication;
import com.example.test.task.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestTaskApplication.class)
@WebAppConfiguration
public class UserServiceImplIntegrationTest {

    @Autowired
    private UserService service;

    @Autowired
    private PasswordEncoder encoder;

    @Test(expected=UsernameNotFoundException.class)
    public void testGetByNameExeption() throws Exception {
        service.getUserByName("NotExistUser");
    }

    @Test
    public void createUserTest() throws  Exception {
        service.createNewUser("NewUser","password");
        User loadedUser = service.getUserByName("NewUser");
        assertEquals(loadedUser.getName(), "NewUser");
        assertTrue(encoder.matches("password", loadedUser.getPassword()));
    }
}