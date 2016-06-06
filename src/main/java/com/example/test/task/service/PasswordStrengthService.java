package com.example.test.task.service;

import com.example.test.task.domain.Strength;

public interface PasswordStrengthService {
    Strength checkStrenght(String password);
}
