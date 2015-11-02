package com.saienko.service;

import com.saienko.bean.User;
import com.saienko.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by gleb on 31.10.2015.
 */
public class UserService {


    @Autowired
    private UserDao dao;

    public User findUserBySsn(String ssn) {
        return dao.findUserBySsn(ssn);
    }

    public List<User> findAllUsers() {
//        List <User> list = new List<User>();
        return null ;
    }

    public boolean isUserSsnUnique(Integer id, String ssn) {
        User user = findUserBySsn(ssn);
        return ( user == null || ((id != null) && (user.getUserId() == id)));
    }


    public void saveUser(User user) {

    }

    public void updateUser(User user) {

    }

    public void deleteUserBySsn(String userSsn) {

    }
}
