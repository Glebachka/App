package com.saienko.cofigurations.converters;

import com.saienko.model.UserRole;
import com.saienko.service.userRoleService.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by gleb on 26.11.2015.
 */
@Component
public class RoleConverter implements Converter<Object, UserRole> {

    @Autowired
    UserRoleService userRoleService;

    public UserRole convert(Object object) {
        Integer id = Integer.parseInt((String) object);
        UserRole role = userRoleService.findById(id);
        return role;
    }
}
