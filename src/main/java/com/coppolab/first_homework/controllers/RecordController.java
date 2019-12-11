package com.coppolab.first_homework.controllers;

import com.coppolab.first_homework.entity.Record;
import com.coppolab.first_homework.entity.User;
import com.coppolab.first_homework.interfaces.UserRepository;
import com.coppolab.first_homework.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping(path = "/record")
public class RecordController {

    @Autowired
    RecordService recordService;


    @PostMapping(path = "/put")
    public @ResponseBody Record addFileRecord(@RequestBody Record record){
        return recordService.saveRecord(record);

    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<User> getUser(@PathVariable int id){
        return recordService.getUser(id);
    }

}
