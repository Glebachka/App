package com.saienko.service.UserService;


import com.saienko.dao.User.UserDao;
import com.saienko.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gleb on 31.10.2015.
 */
@Transactional
@Service("userServiceInterface")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    public User findUserBySsn(String ssn) {
        return dao.findUserByUserSsn(ssn);
    }

    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    public boolean isUserSsnUnique(Integer id, String ssn) {
        User user = findUserBySsn(ssn);
        return (user == null || ((id != null) && (user.getUserId() == id)));
    }

    public User findByUserId(int userId) {
        return dao.findByUserId(userId);
    }

    public void saveUser(User user) {
        dao.saveUser(user);
    }


    public void updateUser(User user) {
        User entity = dao.findByUserId(user.getUserId());
        if (entity != null) {
            entity.setUserName(user.getUserName());
            entity.setUserSsn(user.getUserSsn());
        }
    }

    public void deleteUserByUserSsn(String ssn) {
        dao.deleteUserByUserSsn(ssn);

    }
}
