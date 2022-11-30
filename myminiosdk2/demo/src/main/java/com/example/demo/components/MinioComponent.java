package com.example.demo.components;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import com.example.demo.services.MinIOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MinioComponent {

    @Autowired //This eliminates the need for getters and setters.
    private MinIOService minioService;

    //Si scriu si descarc, pentru verificare.
    public void ReadWriteMinIo(String fileName)
            throws InvalidKeyException, IllegalArgumentException, NoSuchAlgorithmException, IOException {
        minioService.WriteToMinIO(fileName);
        minioService.ReadFromMinIO(fileName);
        System.out.println ("MinIODemo");
    }
}
