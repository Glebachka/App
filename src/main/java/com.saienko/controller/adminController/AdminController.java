package com.saienko.controller.adminController;

import com.saienko.model.User;
import com.saienko.service.userService.UserService;
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
import java.util.Locale;

/**
 * Created by gleb on 21.11.2015.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    MessageSource messageSource;


    /**
     * Medium method for user creation
     */
    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
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

        if (!userService.isUserLoginUnique(user.getUserId(), user.getUserLogin())) {
            FieldError userLoginError = new FieldError("user", "userLogin", messageSource.getMessage("non.unique.login", new String[]{user.getUserLogin()}, Locale.getDefault()));
            result.addError(userLoginError);
            return "registration";
        }

        userService.saveUser(user);
        model.addAttribute("success", "user" + user.getUserName() + " registered complete");
        return "success";
    }

    /**
     * Medium method for update user.
     */
    @RequestMapping(value = {"/edit-{userLogin}-user"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String userLogin, ModelMap model) {

        User user = userService.findUserByLogin(userLogin);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "registration";
    }

    /**
     * Update existing user
     */
    @RequestMapping(value = {"/edit-{userLogin}-user"}, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String userLogin) {
        if (result.hasErrors()) {
            return "registration";
        }
        if (!userService.isUserLoginUnique(user.getUserId(), user.getUserLogin())) {
            FieldError userLoginError = new FieldError("user", "userLogin", messageSource.getMessage("non.unique.login", new String[]{user.getUserLogin()}, Locale.getDefault()));
            result.addError(userLoginError);
            return "registration";
        }
        userService.updateUser(user);
        model.addAttribute("success", "user" + user.getUserName() + " updated complete");
        return "success";
    }

}
