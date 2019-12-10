package com.coppolab.first_homework.services;

import com.coppolab.first_homework.entity.Record;
import com.coppolab.first_homework.entity.User;
import com.coppolab.first_homework.interfaces.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepository;

    public Record post(Record record){
        return recordRepository.save(record);
    }
}
