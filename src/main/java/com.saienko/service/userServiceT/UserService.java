package com.saienko.service.userServiceT;

import com.saienko.model.User;

import java.util.List;

/**
 * Created by gleb on 30.10.2015.
 */
public interface UserService {
    User findByUserId(int userId);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserByUserLogin(String login);

    List<User> findAllUsers();

    User findUserByLogin(String login);

    boolean isUserLoginUnique(Integer id, String login);

}
