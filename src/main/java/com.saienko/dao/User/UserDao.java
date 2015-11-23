package com.saienko.dao.User;

import com.saienko.model.User;

import java.util.List;

/**
 * Created by gleb on 02.11.2015.
 */
public interface UserDao {
    User findByUserId(int userId);
    void saveUser(User user);

    void deleteUserByUserLogin(String Login);
    List<User> findAllUsers();

    User findUserByUserLogin(String Login);
}
