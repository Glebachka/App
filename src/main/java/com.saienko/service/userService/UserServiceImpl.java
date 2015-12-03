package com.saienko.service.userService;


import com.saienko.dao.userDao.UserDao;
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

    public User findUserByLogin(String login) {
        return dao.findUserByUserLogin(login);
    }

    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    public boolean isUserLoginUnique(Integer id, String login) {
        User user = findUserByLogin(login);
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
            entity.setUserLogin(user.getUserLogin());
        }
    }

    public void deleteUserByUserLogin(String login) {
        dao.deleteUserByUserLogin(login);

    }
}
