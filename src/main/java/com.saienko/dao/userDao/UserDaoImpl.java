package com.saienko.dao.userDao;

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

    public User findUserByUserLogin(String userLogin) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("userLogin", userLogin));
        return (User) criteria.uniqueResult();
    }

    public User findByUserId(int userId) {
        return getByKey(userId);
    }


    public void saveUser(User user) {
        persist(user);
    }

    public void deleteUserByUserLogin(String userLogin) {
        Query query = getSession().createSQLQuery("delete FROM User where userssn= :userLogin");
        query.setString("userLogin", userLogin);
        query.executeUpdate();
    }

    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
    }
}
