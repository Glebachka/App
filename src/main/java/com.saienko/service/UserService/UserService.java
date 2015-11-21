package com.saienko.service.UserService;

import com.saienko.model.User;

import java.util.List;

/**
 * Created by gleb on 30.10.2015.
 */
public interface UserService {
    User findByUserId(int userId);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUserByUserSsn(String ssn);
    List<User> findAllUsers();
    User findUserBySsn(String ssn);
    boolean isUserSsnUnique(Integer id, String ssn);

}
