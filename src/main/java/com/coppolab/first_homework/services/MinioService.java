package com.coppolab.first_homework.services;

import com.coppolab.first_homework.entity.User;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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

    public void uploadFile(String bucketName, String objectName, String fileName) {
        try {

            this.minioClient.putObject(bucketName, objectName, fileName);
            System.out.println("File upload successfully");
        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
            e.printStackTrace();

        }


    }

    public String getUrl(String bucketName,String objectName){

        try {
            return this.minioClient.presignedGetObject(bucketName,objectName);
        }catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getFileInfo(String nickname, String objectName){
        List<String> info = new ArrayList<>();
        info.add("BucketName: "+nickname);
        try {
            Iterable<Result<Item>> fileList =  minioClient.listObjects(nickname);
            for(Result<Item> myFile: fileList){

                if(myFile.get().objectName().equals(objectName)){
                    System.out.println("FILE NOME: "+myFile.get().objectName());
                    info.add("ObjectName: "+myFile.get().objectName());
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