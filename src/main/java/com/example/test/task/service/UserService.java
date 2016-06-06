package com.example.test.task.service;

import com.example.test.task.domain.User;

public interface UserService {

    User createNewUser(String name, String password);

    User getUserByName(String name);

}
