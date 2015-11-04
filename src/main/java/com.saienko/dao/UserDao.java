package com.saienko.dao;

import com.saienko.bean.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gleb on 02.11.2015.
 */
@Repository("userDaoInterface")
public class UserDao extends AbstractDao<Integer, User> implements UserDaoInterface {

    public User findUserByUserSsn(String userSsn) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("ssn", userSsn));
        return (User) criteria.uniqueResult();
    }

    public User findByUserId(int userId) {
        return getByKey(userId);
    }

    public void saveUser(User user) {
        persist(user);

    }

    public void deleteUserByUserSsn(String ssn) {
        Query query =getSession().createSQLQuery("delete FROM User where ssn= :ssn");
        query.setString("ssn", ssn);
        query.executeUpdate();
    }

    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
    }



}
