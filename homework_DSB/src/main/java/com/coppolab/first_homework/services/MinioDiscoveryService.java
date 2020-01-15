package com.coppolab.first_homework.services;

import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class MinioDiscoveryService {

    public void resolve(String host) {
        try {
            InetAddress inetAddress = InetAddress.getByName(host);

            System.out.println("Host: " +
                    inetAddress.getHostName());
            System.out.println("IP Address: " +
                    inetAddress.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
