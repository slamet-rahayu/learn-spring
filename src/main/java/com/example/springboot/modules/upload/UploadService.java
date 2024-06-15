package com.example.springboot.modules.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UploadService {

    @Value("${upload.path}")
    private String uploadPath;

    public String uploadFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "";
        }
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(uploadPath).toAbsolutePath().normalize();
        Files.createDirectories(path);
        assert fileName != null;
        Path targetLocation = path.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return uploadPath+"/"+fileName;
    }

    public Resource getFile(String fileName) throws MalformedURLException {
        Path file = Paths.get(uploadPath).resolve(fileName);
        return new UrlResource(file.toUri());
    }

}
