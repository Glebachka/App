package com.saienko.service.photoServiceT;

import com.saienko.dao.photoDaoT.PhotoDao;
import com.saienko.model.Photo;
import com.saienko.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by gleb on 02.12.2015.
 */
@Service("photService")
@Transactional
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoDao photoDao;

    public void savePhoto(Photo photo) {
        photoDao.savePhoto(photo);
    }

    public List<Photo> findAllPhotos(User user) {
        return photoDao.findAllPhotos(user);
    }

    public void deletePhoto(Integer photoId) {
        photoDao.deletePhoto(photoId);

    }

    public Photo fidnPhotoById(Integer photo_id) {
        return photoDao.findPhotoById(photo_id);
    }

    public void updatePhoto(Photo photo) {
        Photo entity = photoDao.findPhotoById(photo.getPhotoId());
        if (entity != null) {
            entity.setPhotoAvatar(photo.getPhotoAvatar());
            entity.setPhotoDescription(photo.getPhotoDescription());
            entity.setPhotoName(photo.getPhotoPath());
            photoDao.savePhoto(entity);
        }
    }
}

