package com.coupang.marketplace.global.util.strorage;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface Storage {

    void saveFile(File file, String fileName);

    void saveMultipartFile(MultipartFile multipartFile, String fileName);

}
