package com.coppolab.first_homework.services;

import com.coppolab.first_homework.config.MinioConfig;
import com.coppolab.first_homework.entity.Record;
import com.coppolab.first_homework.entity.User;
import com.coppolab.first_homework.interfaces.RecordRepository;
import com.coppolab.first_homework.interfaces.UserRepository;
import com.google.gson.Gson;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class MinioService {


    MinioConfig minioConfig;


    private String accesskey;

    private String secretkey;

    private String url;




    private static MinioClient minioClient;
    @Autowired
    private SecurityService securityService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RecordRepository recordRepository;

    public MinioService() throws InvalidPortException, InvalidEndpointException {

        this.minioConfig = new MinioConfig();
        this.secretkey = this.minioConfig.getSecret_key();
        this.accesskey = this.minioConfig.getAccess_key();
        this.url = this.minioConfig.getUrl();
        System.out.println(this.minioConfig.getAccess_key());
        System.out.println(this.secretkey);
        System.out.println(this.url);

        this.minioClient = new MinioClient(url, accesskey, secretkey);
        System.out.println("Minio Client Running");
    }

    public void createBucket(User user) {
        try {
            this.minioClient.makeBucket(user.getNickname());
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (RegionConflictException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoResponseException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        }
        System.out.println("Bucket for user: " + user.getNickname() + " created");
    }

    public ResponseEntity uploadFile(String bucketName, String objectName, String fileName) {
        try {
            //filename = path of the container storage + filename
            this.minioClient.putObject(bucketName, objectName, "/data/"+fileName);
            System.out.println("File upload successfully");
            return new ResponseEntity(HttpStatus.OK);
        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

    public String getUrl(String bucketName,String objectName){

        try {
            return this.minioClient.presignedGetObject(bucketName,objectName);
        }catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String,String> getFileInfo(String nickname, String objectName){
        Map<String,String> info = new HashMap<>();
        info.put("Bucket Name",nickname);
        try {
            /*Un utente può creare più file quindi il solo bucket name == nickname
            * non basta per identificare il file cercato. Devo fare il for e quindi poi
            * fare l'equals con il nome del file all'interno del bucket. Devo passare
            * per forza l'objectName quindi come parametro*/
            Iterable<Result<Item>> fileList =  minioClient.listObjects(nickname);
            for(Result<Item> myFile: fileList){

                if(myFile.get().objectName().equals(objectName)) {

                    info.put("Object Name",myFile.get().objectName());

                    System.err.println("Bucket name: "+info.get((Object)"Bucket Name"));
                    System.err.println("Object name: "+info.get((Object)"Object Name"));

                    return info;
                }

            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoResponseException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFiles(String nickname){

        List<String> files = new ArrayList<>();

        try {
            Iterable<Result<Item>> infos =  minioClient.listObjects(nickname);
            //Fill user's file list
            for(Result<Item> i: infos){
                files.add(i.get().objectName());
            }

            return new Gson().toJson(files);

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoResponseException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAllFiles(){
        try {
            List<Bucket> buckets = minioClient.listBuckets();
            List<String> files = new ArrayList<>();
            for(Bucket b: buckets){
                files.add(this.getFiles(b.name()));
            }
            return new Gson().toJson(files);

        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoResponseException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFilesByUserRole(){
        String nickname = securityService.getAuthenticatedUser();
        Optional<User> u = userRepository.findByNickname(nickname);
        User user = u.get();
        if(user.getRoles().contains((String)"ADMIN")){
           return this.getAllFiles();
        }
        else{
            return this.getFiles(nickname);
        }
    }

    //DELETE IN ADMIN MODE

    public ResponseEntity<String> deleteAsAdmin(int id){
        Optional<Record> r = recordRepository.findById(id);
        Record record = r.get();
        String bucketName = record.getBucketName();
        String objectName = record.getObjectName();

        try {
            minioClient.removeObject(bucketName,objectName);
            recordRepository.deleteById(id);
            return new ResponseEntity<>("OK",HttpStatus.OK);
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoResponseException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
        //DA CAMBIARE
        return new ResponseEntity<>("NOT OK",HttpStatus.NOT_FOUND);
    }

    //DELETE IN USER MODE
    public ResponseEntity<String> deleteAsUser(int id){
        Optional<Record> r = recordRepository.findById(id);
        Record record = r.get();
        String bucketName = record.getBucketName();
        String objectName = record.getObjectName();
        User user = securityService.getAuthenticatedUserObject();
        if(record.getAuthor().getId()==user.getId()) {
            try {
                minioClient.removeObject(bucketName, objectName);
                recordRepository.deleteById(id);
                return new ResponseEntity<>("OK", HttpStatus.OK);
            } catch (InvalidBucketNameException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InsufficientDataException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (NoResponseException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (ErrorResponseException e) {
                e.printStackTrace();
            } catch (InternalException e) {
                e.printStackTrace();
            } catch (InvalidArgumentException e) {
                e.printStackTrace();
            }
        }else{
            return new ResponseEntity<>("USER NON PROPRIETARIO",HttpStatus.UNAUTHORIZED);
        }
        //DA CAMBIARE
        return new ResponseEntity<>("NOT OK",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteByUserRole(int id){
        User user = securityService.getAuthenticatedUserObject();
        if(user.getRoles().contains("USER")){
           return this.deleteAsUser(id);
        }else if(user.getRoles().contains("ADMIN")){
            return this.deleteAsAdmin(id);
        }
        return new ResponseEntity<>("BAD",HttpStatus.NOT_FOUND);
    }
}