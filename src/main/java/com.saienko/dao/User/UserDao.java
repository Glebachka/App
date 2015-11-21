package com.saienko.dao.User;

import com.saienko.model.User;

import java.util.List;

/**
 * Created by gleb on 02.11.2015.
 */
public interface UserDao {
    User findByUserId(int userId);
    void saveUser(User user);
    void deleteUserByUserSsn(String Ssn);
    List<User> findAllUsers();
    User findUserByUserSsn(String ssn);
}
