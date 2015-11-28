package com.saienko.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by gleb on 27.11.2015.
 */
@Entity
@Table(name = "PHOTO")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PHOTO_ID")
    @NotEmpty
    private int photoId;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "PHOTO_PATHS")
    private String photoPath;

    @Column(name = "PHOTO_NAME")
    private String photoName;

}
