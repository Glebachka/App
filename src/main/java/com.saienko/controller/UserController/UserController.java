package com.saienko.controller.UserController;

import com.saienko.model.Link;
import com.saienko.model.Photo;
import com.saienko.model.User;
import com.saienko.service.LinkService.LinkService;
import com.saienko.service.PhotoService.PhotoService;
import com.saienko.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by gleb on 21.11.2015.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static String UPLOAD_LOCATION = "/tmp/App/uploads";
    pr

    @Autowired
    PhotoService photoService;
    @Autowired
    UserService userService;
    @Autowired
    LinkService linkService;

    @RequestMapping(value = {"/links", "/"}, method = RequestMethod.GET)
    public String findLinks(ModelMap model) {
        List<Link> links = linkService.findAllUserLinks(getCurrentUser());
        model.addAttribute("newLink", new Link());
        model.addAttribute("links", links);
        model.addAttribute("currentUserRole", getCurrentRole());
        model.addAttribute("currentUser", getCurrentUser());
        return "usermainpage";
    }

    @RequestMapping(value = {"/delete-{linkId}"}, method = RequestMethod.GET)
    public String deleteLinkById(@PathVariable Integer linkId) {
        linkService.deleteLinkByID(linkId);
        return getUrl();
    }

    @RequestMapping(value = {"/addLink"}, method = RequestMethod.POST)
    public String saveLink(@ModelAttribute Link link, BindingResult result) {
        link.setUser(getCurrentUser());
        linkService.saveLink(link);
        return getUrl();
    }


//    @RequestMapping(value = {"/edit-{linkId}"}, method = RequestMethod.GET)
//    public String getLink(@PathVariable int linkId, ModelMap model) {
//        Link link = linkService.findLinkById(linkId);
//        model.addAttribute("link", link);
//        return "/${currentUserRole}/links";
//
//    }
//
//    @RequestMapping(value = {"/edit-{linkId}"}, method = RequestMethod.POST)
//    public String updateLink(Link link) {
//        linkService.updateLink(link);
//        return "/${currentUserRole}/links";
//    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String getPhotoUpload(ModelMap model){
        Photo uploadPhoto = new Photo();
        model.addAttribute("uploadPhoto", uploadPhoto);
        return "uploadpage";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadPhoto(BindingResult result, ModelMap modelMap, Photo photo){

        MultipartFile
        return "succeess";
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

    private String getUrl() {
        String url = "redirect:/" + getCurrentRole() + "/links";
        return url;
    }

    private String getCurrentRole() {
        String userRole = getCurrentUser().getUserRoles().toString();
        int size = userRole.length() - 1;
        StringBuilder userRoleSB = new StringBuilder(userRole);
        userRole = userRoleSB.deleteCharAt(size).deleteCharAt(0).toString().toLowerCase();
        return userRole;
    }

}
