package com.saienko.service.PhotoService;

import com.saienko.dao.Photo.PhotoDao;
import com.saienko.model.Photo;
import com.saienko.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by gleb on 02.12.2015.
 */
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoDao photoDao;

    public void savePhoto(Photo photo) {

    }

    public List<Photo> findAllPhotos(User user) {
        return null;
    }

    public void deletePhoto(Integer photoId) {

    }

    public Photo fidnPhotoById(Integer photo_id) {
        return null;
    }

    public Photo updatePhoto(Photo photo) {
        return null;
    }
}
