package com.coppolab.first_homework.controllers;


import com.coppolab.first_homework.contextClasses.MinioFile;
import com.coppolab.first_homework.entity.User;



import com.coppolab.first_homework.services.MinioService;

import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
@RequestMapping (path = "/minio")
public class MinioController {

    @Autowired
    MinioService minioService;




    @PostMapping(path = "/upload")
    public void uploadFile(@RequestBody MinioFile minioFile){
        minioService.uploadFile(minioFile.getBucketName(), minioFile.getObjectName(), minioFile.getFilename());

    }

    @GetMapping(path= "/url/{bucketName},{objectName}")
    public @ResponseBody String getFileUrl(@PathVariable String bucketName, @PathVariable String objectName){
        return minioService.getUrl(bucketName,objectName);
    }

    @GetMapping(path = "/fileName/{nickname},{objectName}")
    public @ResponseBody
    List<String> getFileInfo(@PathVariable String nickname, @PathVariable String objectName){
        return minioService.getFileInfo(nickname,objectName);

   
    }

}
