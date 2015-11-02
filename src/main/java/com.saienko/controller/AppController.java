package com.saienko.controller;


import com.saienko.bean.User;
import com.saienko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by gleb on 28.10.2015.
 */


@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    UserService userServise;

    @Autowired
    MessageSource messageSource;


    /**
     * Method returs all users;
     */
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        List<User> users = userServise.findAllUsers();
        model.addAttribute("users", users);
        return "allusers";
    }

    /**
     * Medium method for user creation
     */
    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newUser (ModelMap model){
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "registration";

    }

    /**
     * Method for Add a new user from form submission.
     */

    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "registration";
        }

        if (!userServise.isUserSsnUnique(user.getUserId(), user.getUserSsn())) {
            FieldError userSsnError = new FieldError("user", "userSsn", messageSource.getMessage("non.unique.ssn", new String[]{user.getUserSsn()}, Locale.getDefault()));
            result.addError(userSsnError);
            return "registration";
        }

        userServise.saveUser(user);
        model.addAttribute("success", "User" + user.getUserName() + " registered complete");
        return "success";
    }

    /**
     * Medium method for update user.
     */
    @RequestMapping (value = {"/edit-{userSsn}-user"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String userSsn, ModelMap model){

        User user = userServise.findUserBySsn(userSsn);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "registration";
    }

    /**
     * Update existing user
     */
    @RequestMapping(value = {"/edit-{userSsn}-user"}, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String userSsn){
        if (result.hasErrors()){
            return "registration";
        }
        if (!userServise.isUserSsnUnique(user.getUserId(), user.getUserSsn())){
            FieldError userSsnError = new FieldError("user", "userSsn", messageSource.getMessage("non.unique.ssn", new String[]{user.getUserSsn()}, Locale.getDefault()));
        }
        userServise.updateUser(user);
        model.addAttribute("success", "User" +  user.getUserName() + " updated complete");
        return "success";
    }

    /**
    * Method for delete user
     */
    @RequestMapping(value = {"/delete-{userSsn}-user"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String userSsn){
        userServise.deleteUserBySsn(userSsn);
        return "redirect:/list";
    }

}
