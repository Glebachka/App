package com.saienko.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by gleb on 02.12.2015.
 */

public class FileBucket {

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

     MultipartFile file;
}
