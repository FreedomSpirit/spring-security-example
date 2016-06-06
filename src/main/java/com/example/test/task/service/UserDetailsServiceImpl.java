package com.example.test.task.service;


import com.example.test.task.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByName(username);

        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(
                        user.getName(),
                        user.getPassword(),
                        roles);

        return userDetails;
    }
}
