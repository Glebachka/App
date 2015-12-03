package com.saienko.controller.UserController;

import com.saienko.model.*;
import com.saienko.model.UtilClasses.PhotoPhotoBucket;
import com.saienko.service.LinkService.LinkService;
import com.saienko.service.PhotoService.PhotoService;
import com.saienko.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by gleb on 21.11.2015.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(String.valueOf(UserController.class));

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


    @Autowired
    PhotoBucketValidator photoBucketValidator;

    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(photoBucketValidator);
    }


    @RequestMapping(value = "/uploadpage", method = RequestMethod.GET)
    public String getPhotoUpload(ModelMap model) {
//        Photo newPhoto = new Photo();
//        PhotoBucket photoBucket = new PhotoBucket();
        PhotoPhotoBucket photoPhotoBucket = new PhotoPhotoBucket();
//        model.addAttribute("newPhoto", newPhoto);
//        model.addAttribute("photoBucket", photoBucket);
        model.addAttribute("photoPhotoBucket", photoPhotoBucket);
        return "uploadpage";
    }

    @RequestMapping(value = "/uploadpage", method = RequestMethod.POST)
//    public String uploadPhoto(@Valid @RequestPart("photoBucket") PhotoBucket photoBucket,
//                              @RequestPart("newPhoto") Photo photo,
//                              ModelMap model, BindingResult result)  {
//    public String uploadPhoto(@Valid PhotoBucket fileBucket, ModelMap model, BindingResult result) throws IOException {
    public String uploadPhoto(PhotoPhotoBucket photoPhotoBucket, ModelMap model, BindingResult result) throws IOException {

        Photo photo = photoPhotoBucket.getPhoto();
        PhotoBucket photoBucket = photoPhotoBucket.getPhotoBucket();
        if (result.hasErrors()) {
            System.out.println("validation errors");
            return "uploadpage";
        } else {
            MultipartFile multipartFile = photoBucket.getFile();
            File checkDirectory = new File(getUploadPath());
            if (!checkDirectory.exists()) {
                boolean makeDirs = false;
                try {
                    checkDirectory.mkdirs();
                    makeDirs = true;
                } catch (SecurityException se) {
                    logger.config("dir creation error in UserController" + se);
                }
            }

            try {
                FileCopyUtils.copy(photoBucket.getFile().getBytes(), new File(getUploadPath() + photoBucket.getFile().getOriginalFilename()));
            } catch (IOException e) {
                logger.config("File copy calls problem in UserController" + e);
            }
            photoService.savePhoto(photo);

//            String fileName = multipartFile.getOriginalFilename();
//            model.addAttribute("fileName", fileName);
            return "successupload";
        }
    }


//        MultipartFile multipartFile = photoBucket.getFile();
//
//        try {
//            FileCopyUtils.copy(photoBucket.getFile().getBytes(), new File(getUploadPath() + photoBucket.getFile().getOriginalFilename()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String fileName = multipartFile.getOriginalFilename();
//        model.addAttribute("fileName", fileName);
//        photoService.savePhoto(photo);
//        return "successupload";


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

    private String getUploadPath() {
        String uploadLocation = "/tmp/App/uploads/" + getCurrentUser().getUserLogin() + "/";
        return uploadLocation;
    }
}
