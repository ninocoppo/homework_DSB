package com.coppolab.first_homework.controllers;

import com.coppolab.first_homework.contextClasses.RecordRequest;
import com.coppolab.first_homework.entity.Record;
import com.coppolab.first_homework.entity.User;
import com.coppolab.first_homework.interfaces.UserRepository;
import com.coppolab.first_homework.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    @PostMapping(path = "/put" )
    public @ResponseBody ResponseEntity<Record> addFileRecord(@RequestBody RecordRequest recordRequest){
        return recordService.saveRecord(recordRequest);

    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<User> getUser(@PathVariable int id){
        return recordService.getUser(id);
    }

    @PostMapping(path = "/check/{id}")
    public ResponseEntity checkRecord(@PathVariable int id){
       return recordService.checkRecord(id);
    }

    @PostMapping(path="/update/{id},{objectName}")
    public @ResponseBody ResponseEntity<Record> updateRecord(@PathVariable int id,@PathVariable String objectName){
        return recordService.updateRecord(id,objectName);
    }

    @GetMapping(path="/showRecord/{id}")
    public @ResponseBody ResponseEntity<String> showRecord(@PathVariable int id){
        return recordService.getRecord(id);
    }

}

