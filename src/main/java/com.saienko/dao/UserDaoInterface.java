package com.saienko.dao;

import com.saienko.bean.User;

import java.util.List;

/**
 * Created by gleb on 02.11.2015.
 */
public interface UserDaoInterface {
    User findByUserId(int userId);
    void saveUser(User user);
    void deleteUserByUserSsn(String Ssn);
    List<User> findAllUsers();
    User findUserByUserSsn(String ssn);
}
