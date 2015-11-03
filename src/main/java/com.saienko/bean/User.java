package com.saienko.bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by gleb on 25.10.2015.
 */
@Entity
@Table(name = "User")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Size(min = 3, max = 50)
    @Column(name = "NAME", nullable = false)
    private String userName;

    @NotNull
    @Column(name = "SSN", unique = true, nullable = false)
    private String userSsn;


    public String getUserSsn() {
        return userSsn;
    }

    public void setUserSsn(String userSsn) {
        this.userSsn = userSsn;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof User)) return false;
        User otherUser = (User) obj;
        if (userId != otherUser.userId) return false;
        if (userSsn == null) {
            if (otherUser.userSsn != null) return false;
        } else {
            if (!userSsn.equals(otherUser.userSsn)) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + userId + ", name=" + userName + ", userSsn=" + userSsn + "]";
    }

}
