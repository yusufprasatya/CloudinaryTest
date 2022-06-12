package com.cloudinary.cloudinaryTest.component;

import com.cloudinary.cloudinaryTest.service.FileUploadService;
import com.cloudinary.cloudinaryTest.util.FileBucket;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FileValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return FileUploadService.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FileBucket bucket = (FileBucket) target;

        if (bucket.getFile() != null && bucket.getFile().isEmpty()) {
            errors.rejectValue("file", "file.empty");
        }
    }
}
