package com.coppolab.first_homework.services;

import com.coppolab.first_homework.entity.User;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinioService {

    private String acceskey = "FIG19SZE1SKE59BJ0A63";
    private String secretkey = "OY3enw94yeTK7sppVT+iIEydkS0E9+buJf00PtU1";

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
}
