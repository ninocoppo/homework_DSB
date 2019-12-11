package com.coppolab.first_homework.controllers;

import com.coppolab.first_homework.contextClasses.HelpBucket;
import com.coppolab.first_homework.services.MinioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping (path = "/minio")
public class MinioController {

    @Autowired
    MinioService minioService;



    @PostMapping(path = "/post/{id}")
    public @ResponseBody String uploadFile(@PathVariable int id, @RequestBody HelpBucket helpBucket){
        minioService.uploadFile(helpBucket.getBucketName(),helpBucket.getObjectName(), helpBucket.getFileName());
        return minioService.getUrl(helpBucket.getBucketName(),helpBucket.getObjectName());
    }

}
