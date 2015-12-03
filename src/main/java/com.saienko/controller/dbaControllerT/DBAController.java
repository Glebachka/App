package com.saienko.controller.dbaControllerT;

import com.saienko.model.User;
import com.saienko.model.UserRole;
import com.saienko.service.userRoleServiceT.UserRoleService;
import com.saienko.service.userServiceT.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by gleb on 21.11.2015.
 * DBA controller class. Includes all allowed actions with users and admins.
 */
@Controller
@SessionAttributes("roles")
@RequestMapping("/dba")
public class DBAController {

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    UserService userService;

    @Autowired
    MessageSource messageSource;

    /**
     * Method for delete user
     */
    @RequestMapping(value = {"/delete-{userLogin}-user"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String userLogin) {
        userService.deleteUserByUserLogin(userLogin);
        return "redirect:/dba/list";
    }

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
        return "redirect:/dba/list";
    }


    /**
     * Method for get role-types.
     */
    @ModelAttribute("roles")
    public List<UserRole> initializeRoles() {
        return userRoleService.findAllUserRole();
    }

    /**
     * Medium method for update user
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
        model.addAttribute("success", "user " + user.getUserName() + " updated complete");
        return "success";
    }



    /**
     * This method returns current user
     *
     * @return
     */
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

    /**
     * Thos method returns current role of the user
     *
     * @return
     */
    private String getCurrentRole() {
        String userRole = getCurrentUser().getUserRoles().toString();
        int size = userRole.length() - 1;
        StringBuilder userRoleSB = new StringBuilder(userRole);
        userRole = userRoleSB.deleteCharAt(size).deleteCharAt(0).toString().toLowerCase();
        return userRole;
    }
}
