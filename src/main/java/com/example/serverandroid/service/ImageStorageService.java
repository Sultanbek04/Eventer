package com.example.serverandroid.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {

    void uploadImage(MultipartFile file);//загружать на сервер

    byte[] downloadImage(String name);

}
