package com.saienko.dao.Photo;

import com.saienko.model.Photo;

import java.util.List;

/**
 * Created by gleb on 01.12.2015.
 */
public interface PhotoDao {
    Photo savePhoto();
    void deletePhoto();
    List<Photo> findAllPhotos();
    Photo findPhotoById(Integer id);
    Photo findPhotoByName(String name);

}
