package com.saienko.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gleb on 25.10.2015.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Size(min = 3, max = 50)
    @Column(name = "USERNAME", nullable = false)
    private String userName;

    @NotEmpty
    @Column(name = "USERSSN", unique = true, nullable = false)
    private String userLogin;

    @NotEmpty
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "APP_USER_USER_ROLE", joinColumns = {@JoinColumn(name = "USERID")},
            inverseJoinColumns = {@JoinColumn(name = "ID")})
    private Set<UserRole> userRoles = new HashSet<UserRole>();

    @OneToMany
    private Set<Link> links;

    @OneToMany
    private Set<Photo> photos;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_BANK", joinColumns = {@JoinColumn(name = "USERID")},
            inverseJoinColumns = {@JoinColumn(name = "BANK_ID")})
    private Set<Bank> banks;

    @Column(name = "USERPASSWORD", nullable = false)
    private String userPassword;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<Link> getLinks() {
        return links;
    }

    public void setLinks(Set<Link> links) {
        this.links = links;
    }

    public Set<Bank> getBanks() {
        return banks;
    }

    public void setBanks(Set<Bank> banks) {
        this.banks = banks;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getUserId() != user.getUserId()) return false;
        if (!getUserName().equals(user.getUserName())) return false;
        if (!getUserLogin().equals(user.getUserLogin())) return false;
        if (!getUserRoles().equals(user.getUserRoles())) return false;
        return getUserPassword().equals(user.getUserPassword());

    }

    @Override
    public int hashCode() {
        int result = getUserId();
        result = 31 * result + getUserName().hashCode();
        result = 31 * result + getUserLogin().hashCode();
        result = 31 * result + getUserRoles().hashCode();
        result = 31 * result + getUserPassword().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "user{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", userRoles=" + userRoles +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
