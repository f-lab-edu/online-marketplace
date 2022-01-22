package com.coupang.marketplace.global.util.strorage;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Component
public class NcpStorage implements Storage {

    @Value("${ncp.object-storage.end-point}")
    private String endPoint;

    @Value("${ncp.object-storage.region-name}")
    private String regionName;

    @Value("${ncp.object-storage.access-key}")
    private String accessKey;

    @Value("${ncp.object-storage.secret-key}")
    private String secretKey;

    @Value("${ncp.object-storage.bucket-name}")
    private String bucketName;

    @Override
    public void saveMultipartFile(MultipartFile multipartFile, String fileName){
        try {
            File file = convertToFile(multipartFile);
            saveFile(file, fileName);
        } catch (AmazonServiceException e) {
            throw new RuntimeException("아마존 API 요청에서 오류가 발생했습니다.", e);
        } catch (Exception e){
            throw new RuntimeException("multipart 파일 저장을 실패했습니다.", e);
        }
    }

    @Override
    public void saveFile(File file, String fileName){
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();

        s3.putObject(bucketName, fileName, file);
    }

    private File convertToFile(MultipartFile multipartFile) {
        try {
            File tempFile = File.createTempFile("temp", null, null);
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(multipartFile.getBytes());
                return tempFile;
            }
        } catch (Exception e) {
            throw new RuntimeException("multipart 파일 컨버팅을 실패했습니다.", e);
        }
    }
}
