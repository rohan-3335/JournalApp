package com.SpringBootProject.journalApp.services;

import com.SpringBootProject.journalApp.Entity.UserEntity;
import com.SpringBootProject.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
//
//import java.util.List;

@Component
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);

        if (user != null) {
            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())  // Password must be hashed
                    .roles(user.getRoles().toArray(new String[0]))  // Assign roles properly
                    .build();
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }

}
