package com.coppolab.first_homework.services;

import com.coppolab.first_homework.entity.Record;
import com.coppolab.first_homework.entity.User;
import com.coppolab.first_homework.interfaces.RecordRepository;
import com.coppolab.first_homework.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    UserRepository userRepository;

    public Record saveRecord(Record record){

        Optional<User> u = userRepository.findById(record.getAuthorId());
        User user = u.get();

        record.setAuthor(user);
        System.out.println("Record: "+record);

        return recordRepository.save(record);
    }

    public ResponseEntity<User> getUser(int id){
        Optional<User> u = userRepository.findById(id);

        try{
            User user = u.get();

            return new ResponseEntity<User>(user,HttpStatus.OK);
        }catch(NoSuchElementException e){

            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }
}
