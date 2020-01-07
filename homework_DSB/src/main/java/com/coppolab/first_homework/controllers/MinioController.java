package com.coppolab.first_homework.controllers;


import com.coppolab.first_homework.contextClasses.MinioFile;
import com.coppolab.first_homework.entity.User;



import com.coppolab.first_homework.services.MinioService;

import io.minio.Result;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


@Controller
@RequestMapping (path = "/minio")
public class MinioController {

    @Autowired
    MinioService minioService;




    @PostMapping(path = "/upload")
    public ResponseEntity uploadFile(@RequestBody MinioFile minioFile){

        return minioService.uploadFile(minioFile.getBucketName(), minioFile.getObjectName(), minioFile.getFilename());

    }

    @GetMapping(path= "/url/{bucketName},{objectName}")
    public @ResponseBody String getFileUrl(@PathVariable String bucketName, @PathVariable String objectName){
        return minioService.getUrl(bucketName,objectName);
    }

    @GetMapping(path = "/fileName/{nickname},{objectName}")
    public @ResponseBody
    Map<String, String> getFileInfo(@PathVariable String nickname, @PathVariable String objectName){
        return minioService.getFileInfo(nickname,objectName);

   
    }

    /*Return all files owned by a user*/
    @GetMapping(path = "/userFiles/{nickname}")
    public @ResponseBody String getFiles(@PathVariable String nickname){
        return minioService.getFiles(nickname);
    }

    /*Return all files stored in the storage*/
    @GetMapping(path= "/allFiles")
    public @ResponseBody String getAllFiles(){
        return minioService.getAllFiles();
    }

    /*If user role == ADMIN return all files in the storage else return auth user's files*/
    @GetMapping (path ="files")
    public @ResponseBody String getFilesByUserRole(){
        return minioService.getFilesByUserRole();
    }


    //DELETE by userROLE

    @DeleteMapping(path = "/deleteByUserRole/{id}")

    public @ResponseBody ResponseEntity<String> deleteByUserRole(@PathVariable int id){

        return minioService.deleteByUserRole(id);
    }




}
