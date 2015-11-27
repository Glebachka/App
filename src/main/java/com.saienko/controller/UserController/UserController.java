package com.saienko.controller.UserController;

import com.saienko.model.Link;
import com.saienko.model.User;
import com.saienko.service.LinkService.LinkService;
import com.saienko.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by gleb on 21.11.2015.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    LinkService linkService;

    @RequestMapping(value = {"/links", " / "}, method = RequestMethod.GET)
    public String findLinks(ModelMap model) {

        List<Link> links = linkService.findAllUserLinks(getCurrentUser());
        model.addAttribute("links", links);
        return "usermainpage";
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
