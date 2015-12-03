package com.saienko.service.PhotoService;

import com.saienko.model.Photo;
import com.saienko.model.User;

import java.util.List;

/**
 * Created by gleb on 02.12.2015.
 */
public interface PhotoService {

    void savePhoto(Photo photo);

    List<Photo> findAllPhotos(User user);

    void deletePhoto(Integer photoId);

    Photo fidnPhotoById(Integer photo_id);

    void updatePhoto(Photo photo);
}
