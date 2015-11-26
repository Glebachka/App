package com.saienko.service.UserRoleService;

import com.saienko.model.UserRole;

import java.util.List;

/**
 * Created by gleb on 25.11.2015.
 */
public interface UserRoleService {
    List<UserRole> findAllUserRole();

    UserRole findById(int id);

    UserRole findByRole(String role);
}
