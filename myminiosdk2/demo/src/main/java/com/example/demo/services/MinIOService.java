package com.example.demo.services;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import io.minio.BucketExistsArgs;
import io.minio.DownloadObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;

@Service
public class MinIOService {

    final static String endPoint = "http://192.168.100.30:9000";
    final static String accessKey = "admin";
    final static String secretKey = "password";
    final static String bucketName = "mybucket"; //Ideea ar fi or sa am un bucket pentru fiecare, ori sa am un singur bucket si fisiere unice pentru fiecare user.
    // De preferat prima varianta
    //aici trebuie sa ma joc
    final static String localFileFolderUpload = "D:\\Anul4\\An4Sem1 IngineriaProgramarii\\ProiectIP\\files\\";
    final static String localFileFolderDownload = "D:\\Anul4\\An4Sem1 IngineriaProgramarii\\ProiectIP\\files\\downloads\\";
    public void WriteToMinIO(String fileName)
            throws InvalidKeyException, IllegalArgumentException, NoSuchAlgorithmException, IOException {
        try { //Ma conectez-> creez clientul folosind metoda builder() cu credentialele potrivite
            MinioClient minioClient = MinioClient.builder().endpoint(endPoint)
                    .credentials(accessKey, secretKey).build();

            //Verific daca bucket-ul exista.
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

            //Daca bucket-ul nu exista, il creez.
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            //Voi uploada fisierul.
            String fileToUpload = localFileFolderUpload + fileName;
            UploadObjectArgs args = UploadObjectArgs.builder().bucket(bucketName).object(fileName)
                    .filename(fileToUpload).build();
            minioClient.uploadObject(args);


            System.out.println(fileToUpload + " successfully uploaded to:");
            System.out.println("   container: " + bucketName);
            System.out.println("   blob: " + fileName);
            System.out.println();
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    public void ReadFromMinIO(String fileName)
            throws InvalidKeyException, IllegalArgumentException, NoSuchAlgorithmException, IOException {
        try {
            //Cream clientul. In viitor ar trebui sa pun credentialele in variabilele de environment. Use case pentru securitate.
            MinioClient minioClient = MinioClient.builder().endpoint(endPoint)
                    .credentials(accessKey, secretKey).build();

            //Descarc fisierul.
            String downloadedFile = localFileFolderDownload+fileName;
            DownloadObjectArgs args = DownloadObjectArgs.builder().bucket(bucketName).object(fileName)
                    .filename(downloadedFile).build();
            minioClient.downloadObject(args);

            //Afisez
            System.out.println("Downloaded file to ");
            System.out.println(" " + downloadedFile);
            System.out.println();
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

}
