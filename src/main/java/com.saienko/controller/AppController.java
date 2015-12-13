package com.saienko.controller;
import com.saienko.model.User;
import com.saienko.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    String currentUserName = getCurrentUser().getUserName();
    String currentUserRole =  getCurrentRole();

    /**
     * Method returns all users;
     */
    @RequestMapping(value = {"/list", "/dba/list", "/dba"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {


        List<User> users = userService.findAllUsers();
        model.addAttribute("currentUserName", currentUserName);
        model.addAttribute("currentUserRole", currentUserRole);
        model.addAttribute("users", users);
        return "allusers";
    }

    /**
     * This is redirect to admin for work with banks
     * @return
     */
    @RequestMapping(value = {"/admin"}, method=RequestMethod.GET)
    public String getBanks(){
        return "getbanks";
    }

    /**
     * Login
     *
     * @return
     */
    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    /**
     * Logout
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    /**
     * This is the controller which react on user less priority.
     * @param model
     * @return
     */
    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("useName", getCurrentUser().getUserName());
        return "accessDenied";
    }

    /**
     * Returns current user.
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
     * Returns user role.
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
