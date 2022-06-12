package com.cloudinary.cloudinaryTest.controller;

import com.cloudinary.cloudinaryTest.config.CloudinaryConfig;
import com.cloudinary.cloudinaryTest.service.FileUploadService;
import com.cloudinary.utils.ObjectUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.cloudinary.Cloudinary;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/image")
public class FileUploadController {

    @Autowired
    private CloudinaryConfig cloudinary;

    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping("/upload")
    public Map<String, Object> upload() throws IOException {
        File file = new File("/home/yusuf04/Documents/Java/spring/cloudinaryTest/src/main/resources/Screenshot from 2022-01-08 14-56-48.png");
        Map upload = cloudinary.cloudinary().uploader().upload(file, ObjectUtils.emptyMap());
        return upload;
    }

    @PostMapping("/upload")
    public Map<String, Object> uploadFile(@RequestParam MultipartFile file) throws IOException {
        Map<String, Object> storeFile = fileUploadService.store(file);
        return storeFile;
    }

//    @PostMapping("/upload-multiple")
//    public Map<String, Object> uploadFile(@RequestParam MultipartFile[] files) throws IOException {
//
//        String fileUploaded = this.fileUploadService.store(files);
//        return null;
//    }

//    public static File convert(MultipartFile file) throws IOException {
//        Path root = Paths.get("/home/yusuf04/Documents/Java/spring/cloudinaryTest/src/main/resources/uploads");
//        System.out.println(file);
//        String name = storeFile(file, root);
//        File convFile = new File(root+"/"+name);
//        return convFile;
//    }
//
//    public static String storeFile(MultipartFile file,Path path) {
//
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        try {
//            if (fileName.contains("..")) {
//                throw new FileStorageException("Name invalid " + fileName);
//            }else if(file.getContentType() != "png" ||
//                    file.getContentType() != "jpg" ||
//                    file.getContentType() != "jpeg"){
//                throw new  FileStorageException("File not supported");
//            }else if(file.getSize() >= 1064){
//                throw new FileStorageException("File size is too big");
//            }
//            Path targetLocation = path.resolve(fileName);
//            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//            return fileName;
//        } catch (IOException ex) {
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//        }
//    }
}
