package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



import com.example.demo.components.MinioComponent;
import com.example.demo.services.MinIOService;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeyException {
		ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
		String fileName = "user3/diploma3.txt"; //fisierul meu din calea data de mine (aici trebuie sa ma joc)
		MinioComponent minIOComponent = applicationContext.getBean(MinioComponent.class);
		minIOComponent.ReadWriteMinIo(fileName); //break

		System.out.println ("Done");//sa stiu ca am terminat
	}

}
