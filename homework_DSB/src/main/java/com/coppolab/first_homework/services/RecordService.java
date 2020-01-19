package com.coppolab.first_homework.services;

import com.coppolab.first_homework.contextClasses.RecordRequest;
import com.coppolab.first_homework.entity.Record;
import com.coppolab.first_homework.entity.User;
import com.coppolab.first_homework.interfaces.RecordRepository;
import com.coppolab.first_homework.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityService securityService;



    @Autowired
    MinioService minioService;

    public ResponseEntity<Record> saveRecord(RecordRequest recordRequest){

        //Get current authenticated user
        String nickname = securityService.getAuthenticatedUser();
        try {
            if (nickname.equals(recordRequest.getNickname())) {
                Optional<User> u = userRepository.findByNickname(nickname);
                User user = u.get();

                Record record = new Record();


                record.setAuthor(user);
                record.setFilename(recordRequest.getFilename());

                record.setAuthor(user);

                //SET STATUS OF THE RECORD
                record.setStatus("WaitingUpload");


                System.out.println("Record: " + record);

                return new ResponseEntity<Record>(recordRepository.save(record),HttpStatus.OK);
            }
            else{
                return new ResponseEntity<Record>(HttpStatus.UNAUTHORIZED);
            }
            }catch(NoSuchElementException e){
                e.printStackTrace();
        }

        return new ResponseEntity<Record>(HttpStatus.NOT_FOUND);

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

    public ResponseEntity<Record> checkRecord(int id){

        /*Sto supponendo che l'id passato dal client sia l'id del record*/
        Optional<Record> r = recordRepository.findById(id);
        Record record = r.get();

        if (record.getStatus().equals("WaitingUpload")) {
            record.setStatus("Uploades");
            recordRepository.save(record);
            User user = record.getAuthor();
            String nickname = user.getNickname();
            /*If the record is associated to the authenticated user*/
            if (nickname.equals(securityService.getAuthenticatedUser())) {
                Optional<Record> r1 = recordRepository.findById(id);
                Record record1 = r1.get();
                record1.setStatus("Uploading");
                recordRepository.save(record1);
                minioService.uploadFile(nickname, record.getFilename(), record.getFilename(), id);
                this.updateRecord(id, record.getFilename());

                return new ResponseEntity(record, HttpStatus.ACCEPTED);
            }
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Record> updateRecord(int id, String objectName) {
        /*Sto supponendo che l'id passato dal client sia l'id del record*/
        Optional<Record> r = recordRepository.findById(id);
        Record record = r.get();
        Map<String,String> fileInfo;

        fileInfo = minioService.getFileInfo(record.getAuthor().getNickname(),objectName);
        System.out.println("NOME RECORDOPOOOOKLEç"+fileInfo);
        record.setBucketName(fileInfo.get((String)"Bucket Name"));
        record.setObjectName(fileInfo.get((String)"Object Name"));

        return new ResponseEntity<>(record,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> getRecord(int id){

        Optional<Record> r = recordRepository.findById(id);

        Record record = r.get();

        String url = "";

        User user = securityService.getAuthenticatedUserObject();

        //check if the user is the owner of the record
        if(record.getAuthor().getId()==user.getId()){
            System.out.println("Lo user è il proprietario!!!!");
            url= minioService.getUrl(record.getBucketName(),record.getObjectName());
            return new ResponseEntity<>(url,HttpStatus.MOVED_PERMANENTLY);
        }
        return new ResponseEntity<>("NOT FOUND",HttpStatus.NOT_FOUND);
    }




}
