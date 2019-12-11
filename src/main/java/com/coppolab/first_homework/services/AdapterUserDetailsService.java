/*package com.coppolab.first_homework.services;

import com.coppolab.first_homework.entity.User;
import com.coppolab.first_homework.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Base64.getEncoder;

@Service
@Transactional
public class AdapterUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException{
        Optional<User> byNickname = userRepository.findByNickname(nickname);
        User user = byNickname.get();
        if(user==null){
            throw new UsernameNotFoundException("User not exist into database");
        }else{
            return new org.springframework.security.core.userdetails.User(user.getNickname(),getEncoder().encode(user.getPassword()),
                    true,
                    true,
                    true,
                    true,
                    getAuth(user.getRoles())
            );
        }
    }


    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }

    public List<GrantedAuthority> getAuth(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(final String role:roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

}*/