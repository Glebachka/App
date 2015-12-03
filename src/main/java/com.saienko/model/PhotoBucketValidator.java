package com.saienko.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by gleb on 02.12.2015.
 */
@Component
public class PhotoBucketValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return PhotoBucket.class.isAssignableFrom(clazz);
    }

    public void validate(Object obj, Errors errors) {
        PhotoBucket file = (PhotoBucket) obj;
        if(file.getFile()!=null){
            if (file.getFile().getSize() == 0) {
                errors.rejectValue("file", "missing.file");
            }
        }
    }
}
