package com.threeball.threeballcopy.config;

import com.threeball.threeballcopy.entities.User;
import com.threeball.threeballcopy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetails implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public  UserDetails loadUserByUsername(String email)   {
        User user = userRepository.getByEmail(email);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
    }
}
