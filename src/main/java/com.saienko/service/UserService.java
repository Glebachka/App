package com.saienko.service;

import com.saienko.bean.User;
import com.saienko.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by gleb on 31.10.2015.
 */
@Transactional
@Service("userServiceInterface")
public class UserService implements UserServiceInterface {

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

    public User findbyUserId(int userId) {
        return dao.findByUserId(userId);
    }

    public void saveUser(User user) {
        dao.saveUser(user);
    }

    /*
    * Since the method is running with Transaction, No need to call hibernate update explicitly.
    * Just fetch the entity from db and update it with proper values within transaction.
    * It will be updated in db once transaction ends.
    */
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
