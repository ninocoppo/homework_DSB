package com.coppolab.first_homework.services;

import com.coppolab.first_homework.entity.User;
import io.minio.MinioClient;
import io.minio.ServerSideEncryption;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParserException;

import javax.crypto.KeyGenerator;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service
public class MinioService {


    private String acceskey = "J53UUAWAO16J2S5F9XY7";
    private String secretkey = "eiM19zTwP9TqEnXiCvs0im4dYyiaq1hhHEreVEyp";

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
           /* KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            ServerSideEncryption serverSideEncryption = ServerSideEncryption.withCustomerKey(keyGenerator.generateKey());
            */
            this.minioClient.putObject(bucketName, objectName, fileName);
            System.out.println("File upload successfully");

        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
            e.printStackTrace();

        }


    }

    public String getUrl(String bucketName,String objectName){

        try {
            System.out.println("#NAPPA"+this.minioClient.presignedGetObject(bucketName,objectName));
            return this.minioClient.presignedGetObject(bucketName,objectName);
        }catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }
}