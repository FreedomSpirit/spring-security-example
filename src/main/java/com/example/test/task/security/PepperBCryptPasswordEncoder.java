package com.example.test.task.security;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



public class PepperBCryptPasswordEncoder extends BCryptPasswordEncoder{
    private final String PEPPER = "$s701ERgj-=?kl;r%^&$(&*@z sfS]H#$%";

    private CharSequence pepper(CharSequence rawPassword){
        return rawPassword + PEPPER;
    }

    @Override
    public String encode(CharSequence rawPassword){
        return super.encode(pepper(rawPassword));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword){
        return super.matches(pepper(rawPassword), encodedPassword);
    }
}
