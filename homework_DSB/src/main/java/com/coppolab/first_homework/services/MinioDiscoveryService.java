package com.coppolab.first_homework.services;

import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class MinioDiscoveryService {

    InetAddress[] ipAddresses;

    public InetAddress[] resolve(String host) {
        try {
            InetAddress inetAddress[] = InetAddress.getAllByName(host);
            //minio-headless-service.default.svc.cluster.local

            for(InetAddress i: inetAddress){
                System.out.println("Host: "+i.getHostName());
                System.out.println("IP Addr: "+i.getHostAddress());
            }
            return inetAddress;

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
