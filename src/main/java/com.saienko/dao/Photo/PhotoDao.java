package com.saienko.dao.Photo;

import com.saienko.model.Photo;
import com.saienko.model.User;

import java.util.List;

/**
 * Created by gleb on 01.12.2015.
 */
public interface PhotoDao {
    Photo savePhoto(Photo photo);
    void deletePhoto();
    List<Photo> findAllPhotos(User user);
    Photo findPhotoById(Integer id);
    Photo findPhotoByName(String name);

}
