package com.saienko.dao;

import com.saienko.bean.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gleb on 02.11.2015.
 */
@Repository
public class UserDao extends AbstractDao<Integer, User> implements UserDaoInterface {

    public User findUserByUserSsn(String userSsn) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("ssn", userSsn));
        return (User) criteria.uniqueResult();
    }

    public User findByUserId(int userId) {
        return null;
    }

    public void saveUser(User user) {

    }

    public void deleteUserByUserSsn(String Ssn) {

    }

    public List<User> findAllUsers() {
        return null;
    }


}
