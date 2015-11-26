package com.saienko.service.UserRoleService;

import com.saienko.dao.UserRole.UserRoleDao;
import com.saienko.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gleb on 25.11.2015.
 */
@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleDao userRoleDao;

    public List<UserRole> findAllUserRole() {
        return userRoleDao.findAllUserRole();
    }

    public UserRole findById(int id) {
        return userRoleDao.findById(id);
    }

    public UserRole findByRole(String role) {
        return userRoleDao.findByRole(role);
    }
}
