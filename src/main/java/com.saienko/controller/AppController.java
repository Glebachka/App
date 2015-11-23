package com.saienko.controller;


import com.saienko.model.User;
import com.saienko.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    UserService userService;

    @Autowired
    MessageSource messageSource;


    /**
     * Method returns all users;
     */
    @RequestMapping(value = {"/list", "/admin/list/", "/dba/list/", "/user/list/", "/admin/", "/user/", "/dba/"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("currentuser", getCurrentUser());
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

        if (!userService.isUserLoginUnique(user.getUserId(), user.getUserLogin())) {
            FieldError userLoginError = new FieldError("user", "userLogin", messageSource.getMessage("non.unique.login", new String[]{user.getUserLogin()}, Locale.getDefault()));
            result.addError(userLoginError);
            return "registration";
        }

        userService.saveUser(user);
        model.addAttribute("success", "User" + user.getUserName() + " registered complete");
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
        if (result.hasErrors()){
            return "registration";
        }
        if (!userService.isUserLoginUnique(user.getUserId(), user.getUserLogin())) {
            FieldError userLoginError = new FieldError("user", "userLogin", messageSource.getMessage("non.unique.login", new String[]{user.getUserLogin()}, Locale.getDefault()));
            result.addError(userLoginError);
            return "registration";
        }
        userService.updateUser(user);
        model.addAttribute("success", "User" +  user.getUserName() + " updated complete");
        return "success";
    }

    /**
    * Method for delete user
     */
    @RequestMapping(value = {"/delete-{userLogin}-user"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String userLogin) {
        userService.deleteUserByUserLogin(userLogin);
        return "redirect:/list";
    }

    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userLogin = "";
        User currentUser = null;

        if (principal instanceof UserDetails) {
            userLogin = ((UserDetails) principal).getUsername();
            currentUser = userService.findUserByLogin(userLogin);
        } else {
            userLogin = principal.toString();
            currentUser = userService.findUserByLogin(userLogin);
        }
        return currentUser;
    }
}
