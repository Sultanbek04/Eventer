package com.example.serverandroid.service;

import com.example.serverandroid.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void uploadImage(MultipartFile file) {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
//        String uuiFile = UUID.randomUUID().toString();
//        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
//        String resultFileName = uuiFile + "." + extension;
//        System.out.println(resultFileName);
        try (FileOutputStream outputStream = new FileOutputStream(uploadDir + "/" + file.getOriginalFilename())) {
            outputStream.write(file.getBytes());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] downloadImage(String name) {
        File fi = new File(uploadPath + "/" + name);
        byte[] fileContent = null;
        try {
            fileContent = Files.readAllBytes(fi.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileContent;

    }
}
