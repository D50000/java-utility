package com.utility.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    @Value("${db.password}") // db.password=ENC(d45K4hsd81lKJksnmskdlnsD==)
    private String dbPassword;

    public void printPassword() {
        System.out.println("Decrypted Password: " + dbPassword);
        // "jasypt" will auto encrypt the "${db.password}" that ENC() in the properties.
    }
}
