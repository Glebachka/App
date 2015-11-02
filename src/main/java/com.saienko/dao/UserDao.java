package com.saienko.dao;

import com.saienko.bean.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * Created by gleb on 02.11.2015.
 */
public class UserDao extends AbstractDao<Integer, User> {

    public User findUserBySsn(String ssn) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("ssn", ssn));
        return (User) criteria.uniqueResult();
    }
}
