package com.cloudinary.cloudinaryTest.service;

import com.cloudinary.cloudinaryTest.config.CloudinaryConfig;
import com.cloudinary.cloudinaryTest.controller.FileStorageException;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
public class FileUploadService {

    @Autowired
    private CloudinaryConfig cloudinaryConfig;

    public Map<String, Object> store(MultipartFile file) throws IOException {
        try {
            if (file.isEmpty()) {
                throw new FileStorageException("Failed to store empty file.");
            }
            else if (!Objects.equals(file.getContentType(), "image/png")&&
                    !Objects.equals(file.getContentType(), "image/jpg")&&
                    !Objects.equals(file.getContentType(), "image/jpeg")){
                throw new FileStorageException("File not supported.");
            }
            File newFile = convert(file);
            Map uploaded = cloudinaryConfig.cloudinary().uploader().upload(newFile, ObjectUtils.emptyMap());
            newFile.delete();
            return uploaded;
        }
        catch (IOException ex) {
            throw new FileStorageException("Could not store file. Please try again!", ex);
        }
    }

    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
