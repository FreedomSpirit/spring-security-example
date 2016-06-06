package com.example.test.task.service;


import com.example.test.task.dao.UserDao;
import com.example.test.task.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User createNewUser(String name, String rawPassword) {
        return userDao.createUser(name, passwordEncoder.encode(rawPassword));
    }

    @Transactional
    public User getUserByName(String name) {
        return userDao.getByName(name);
    }


}
