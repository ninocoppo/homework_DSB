package com.coppolab.first_homework.controllers;

import com.coppolab.first_homework.entity.User;
import com.coppolab.first_homework.services.UserService;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping (path = "/user")

public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/register")
    public @ResponseBody User register(@RequestBody User user) throws IOException, InvalidKeyException, NoSuchAlgorithmException, XmlPullParserException, InternalException, NoResponseException, InvalidBucketNameException, InsufficientDataException, ErrorResponseException, RegionConflictException {

        return userService.register(user);
    }


}
