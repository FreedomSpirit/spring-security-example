package com.example.test.task.service;

import com.example.test.task.domain.Strength;
import org.springframework.stereotype.Service;

@Service("passwordStrengthServiceImpl")
public class PasswordStrengthServiceImpl implements PasswordStrengthService{

    @Override
    public Strength checkStrenght(String password) {
        Integer strength = 0;

        String[] matchers = {
                ".*\\d.*",
                "(?-i).*[a-z].*",
                "(?-i).*[A-Z].*",
                ".*[!@#$%^&*()\\-=\\\\].*",
                ".*[_+/'\",.\\]\\[|].*",
                ".*[<>?{}].*"
        };

        for(String matcher : matchers){
            if(password.matches(matcher)){
                strength++;
            }
        }

        if(strength > 3) { return Strength.HIGH; }
        else if(strength > 2) { return Strength.MEDIUM; }

        return Strength.LOW;
    }
}
