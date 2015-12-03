package com.saienko.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by gleb on 27.11.2015.
 */
@Entity
@Table(name = "PHOTO")
public class Photo {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PHOTO_ID")
    private int photoId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(name = "PHOTO_PATHS")
    private String photoPath;

    @Column(name = "PHOTO_NAME")
    private String photoName;

    @Column(name = "PHOTO_DESCRIPTION")
    private String photoDescription;

    @Column(name = "PHOTO_AVATAR")
    private Boolean photoAvatar;

    public Boolean getPhotoAvatar() {
        return photoAvatar;
    }

    public void setPhotoAvatar(Boolean photoAvatar) {
        this.photoAvatar = photoAvatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;

        Photo photo = (Photo) o;

        if (getPhotoId() != photo.getPhotoId()) return false;
        return getUser().equals(photo.getUser());

    }

    @Override
    public int hashCode() {
        int result = getPhotoId();
        result = 31 * result + getPhotoPath().hashCode();
        result = 31 * result + getPhotoAvatar().hashCode();
        return result;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoDescription() {
        return photoDescription;
    }

    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }
}
