package com.saienko.dao.userRoleDao;

import com.saienko.model.UserRole;

import java.util.List;

/**
 * Created by gleb on 21.11.2015.
 */
public interface UserRoleDao {
    List<UserRole> findAllUserRole();

    UserRole findByRole(String role);

    UserRole findById(int id);
}
