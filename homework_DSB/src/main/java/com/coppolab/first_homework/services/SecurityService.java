package com.coppolab.first_homework.services;

import com.coppolab.first_homework.entity.User;
import com.coppolab.first_homework.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    @Autowired
    UserRepository userRepository;

    public String getAuthenticatedUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return username;
        }
        return null;
    }

    public User getAuthenticatedUserObject(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            Optional<User> u = userRepository.findByNickname(((UserDetails) principal).getUsername());
            User user = u.get();
            return user;

        }
        return null;
    }
}
