package com.coupang.marketplace.global.factory;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileFactory {

    public static final ClassLoader CLASS_LOADER = FileFactory.class.getClassLoader();

    public static MockMultipartFile createMultipartFile(String fileName){
        File file = createFile(fileName);

        try {
            return new MockMultipartFile(
                    "test_1",
                    fileName,
                    "image/png",
                    new FileInputStream(file)
            );
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            throw new IllegalArgumentException("파일을 읽을 수 없습니다.");
        }

    }

    public static File createFile(String fileName){
        return new File(CLASS_LOADER
                            .getResource(fileName)
                            .getFile());
    }
}
