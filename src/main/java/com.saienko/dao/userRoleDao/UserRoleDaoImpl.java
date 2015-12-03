package com.saienko.dao.userRoleDao;

import com.saienko.dao.AbstractDao;
import com.saienko.model.UserRole;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gleb on 21.11.2015.
 */

@Repository("userRoleDao")
public class UserRoleDaoImpl extends AbstractDao<Integer, UserRole> implements UserRoleDao {

    public List<UserRole> findAllUserRole() {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("role"));
        return (List<UserRole>) criteria.list();
    }

    public UserRole findByRole(String role) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("role", role));
        return (UserRole) criteria.uniqueResult();
    }

    public UserRole findById(int id) {
        return getByKey(id);
    }
}
