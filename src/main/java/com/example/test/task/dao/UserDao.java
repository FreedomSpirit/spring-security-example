package com.example.test.task.dao;


import com.example.test.task.domain.User;

public interface UserDao {
    User createUser(String name, String password);

    User getByName(String name);
}
