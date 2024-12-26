package com.cts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.entity.User;
import com.cts.exception.StudentNotFound;
import com.cts.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Loading user by username: {}", username);
        User user = repo.findByEmail(username).orElseThrow(
                () -> {
                    logger.error("User not found: {}", username);
                    return new StudentNotFound("User is invalid");
                }
        );
        logger.info("User found: {}", username);

        return new CustomerUserDetails(user);
    }
}
