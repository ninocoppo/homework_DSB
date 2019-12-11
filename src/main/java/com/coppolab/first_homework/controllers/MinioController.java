package com.coppolab.first_homework.controllers;

import com.coppolab.first_homework.services.MinioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping (path = "/minio")
public class MinioController {

    @Autowired
    MinioService minioService;



    @PostMapping(path = "/post/{id}")
    public String uploadFile(@PathVariable int id,@RequestBody String bucketName,@RequestBody String objectName,@RequestBody String fileName){
        minioService.uploadFile(bucketName, objectName, fileName);
        return minioService.getUrl(bucketName, objectName);
    }

}
