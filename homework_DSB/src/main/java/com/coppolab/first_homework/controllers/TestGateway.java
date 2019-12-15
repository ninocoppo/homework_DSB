package com.coppolab.first_homework.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/test")
public class TestGateway {

    @GetMapping(path = "/testGateway")
    public @ResponseBody
    ResponseEntity<String> test(){
        System.out.println("HEREEEEEEEE");
        return new ResponseEntity("API Gateway works!", HttpStatus.OK);
    }
}
