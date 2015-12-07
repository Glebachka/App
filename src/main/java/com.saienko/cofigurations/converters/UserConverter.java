package com.saienko.cofigurations.converters;

import com.saienko.model.User;
import com.saienko.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<String, User> {
    @Autowired
    UserService userService;

    public User convert(String login) {
        User userFindByLogin = userService.findUserByLogin(login);
        return userFindByLogin;
    }
}
