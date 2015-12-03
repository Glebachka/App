package com.saienko.dao.photoDao;

import com.saienko.dao.AbstractDao;
import com.saienko.model.Photo;
import com.saienko.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gleb on 01.12.2015.
 */
@Repository("photoDao")
public class PhotoDaoImpl extends AbstractDao<Integer, Photo> implements PhotoDao {

    public void savePhoto(Photo photo) {
        persist(photo);
    }

    public void deletePhoto(Integer  photoId) {
        Query query = getSession().createSQLQuery("DELETE FROM photo WHERE photo_id = :photoId");
        query.setInteger("photoId", photoId);
        query.executeUpdate();

    }

    public List<Photo> findAllPhotos(User user) {
        Criteria criteria = getSession().createCriteria(Photo.class);
        criteria.add(Restrictions.eq("user", user));
        return (List<Photo>) criteria.list();
    }

    public Photo findPhotoById(Integer id) {
        return getByKey(id);
    }

    public Photo findPhotoByName(String name) {
        Criteria criteria =createEntityCriteria();
        criteria.add(Restrictions.eq("photoname", name));
        return (Photo)criteria.uniqueResult();
    }
}
