package com.coppolab.first_homework.services;

import com.coppolab.first_homework.entity.User;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MinioService {



    private String acceskey = "PB0WPRHCESQ602Y66QFG";
    private String secretkey = "igf4sYXAGpUmZp9JKteCoBFQFQMh2Dyu7hM+VOZO";

    private static MinioClient minioClient;


    public MinioService() throws InvalidPortException, InvalidEndpointException {
        this.minioClient = new MinioClient("http://127.0.0.1:9000", acceskey, secretkey);
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

            this.minioClient.putObject(bucketName, objectName, fileName);
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
}