package com.saienko.dao.User;

import com.saienko.dao.AbstractDao;
import com.saienko.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gleb on 02.11.2015.
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    public User findUserByUserSsn(String userSsn) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("userSsn", userSsn));
        return (User) criteria.uniqueResult();
    }

    public User findByUserId(int userId) {
        return getByKey(userId);
    }

    //TODO: add method for save role in the app_user_user_role table.
    public void saveUser(User user) {
        persist(user);

    }

    public void deleteUserByUserSsn(String userSsn) {
        Query query = getSession().createSQLQuery("delete FROM User where userssn= :userSsn");
        query.setString("userSsn", userSsn);
        query.executeUpdate();
    }

    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
    }



}
