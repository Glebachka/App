package com.saienko.service;

import com.saienko.bean.User;

import java.util.List;

/**
 * Created by gleb on 30.10.2015.
 */
public interface UserServiceInterface{
    User findbyUserId(int userId);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUserByUserSsn(String ssn);
    List<User> findAllUsers();
    User findUserBySsn(String ssn);
    boolean isUserSsnUnique(Integer id, String ssn);

}
