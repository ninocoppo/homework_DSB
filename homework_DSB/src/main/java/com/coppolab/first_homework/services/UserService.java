package com.coppolab.first_homework.services;

import com.coppolab.first_homework.entity.User;
import com.coppolab.first_homework.interfaces.UserRepository;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MinioService minioService;


    public User register(User user)  {
        if(user.getCheckRole()==1){
            user.setRoles(Collections.singletonList("ADMIN"));
        }else {
            user.setRoles(Collections.singletonList("USER"));
        }
        System.out.println(user.getNickname());

        minioService.createBucket(user);

        return userRepository.save(user);
    }

}
