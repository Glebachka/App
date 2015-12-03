package com.saienko.model.utilClassesTTT;

import com.saienko.model.Photo;
import com.saienko.model.PhotoBucket;

/**
 * Created by gleb on 03.12.2015.
 */
public class PhotoPhotoBucket {

    private Photo photo = new Photo();
    private PhotoBucket photoBucket = new PhotoBucket();

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public PhotoBucket getPhotoBucket() {
        return photoBucket;
    }

    public void setPhotoBucket(PhotoBucket photoBucket) {
        this.photoBucket = photoBucket;
    }
}
