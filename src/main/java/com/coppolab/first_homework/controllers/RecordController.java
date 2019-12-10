package com.coppolab.first_homework.controllers;

import com.coppolab.first_homework.entity.Record;
import com.coppolab.first_homework.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/record")
public class RecordController {

    @Autowired
    RecordService recordService;

    @PostMapping(path = "/post")
    public @ResponseBody Record post(@RequestBody Record record){
        return recordService.post(record);

    }

}
