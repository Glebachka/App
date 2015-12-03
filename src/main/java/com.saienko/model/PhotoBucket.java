package com.saienko.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by gleb on 02.12.2015.
 */

public class PhotoBucket {

     MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
