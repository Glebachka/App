package com.saienko.controller.userController;

import com.saienko.model.Link;
import com.saienko.model.Photo;
import com.saienko.model.PhotoBucket;
import com.saienko.model.User;
import com.saienko.model.utilClasses.PhotoPhotoBucket;
import com.saienko.service.linkService.LinkService;
import com.saienko.service.photoService.PhotoService;
import com.saienko.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

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

    /**
     * this method returns list of user`s links
     * @param model
     * @return
     */
    @RequestMapping(value = {"/links", "/"}, method = RequestMethod.GET)
    public String findLinks(ModelMap model) {
        List<Link> links = linkService.findAllUserLinks(getCurrentUser());
        model.addAttribute("newLink", new Link());
        model.addAttribute("links", links);
        model.addAttribute("currentUserRole", getCurrentRole());
        model.addAttribute("currentUser", getCurrentUser());
        return "usermainpage";
    }

    /**
     * This method delete user link and refreshes the page.
     * @param linkId
     * @return
     */
    @RequestMapping(value = {"/delete-{linkId}"}, method = RequestMethod.GET)
    public String deleteLinkById(@PathVariable Integer linkId) {
        linkService.deleteLinkByID(linkId);
        return getUrl();
    }

    /**
     * This method adds link and refreshed the page.
     * @param link
     * @param result
     * @return
     */
    @RequestMapping(value = {"/addLink"}, method = RequestMethod.POST)
    public String saveLink(@ModelAttribute Link link, BindingResult result) {
        link.setUser(getCurrentUser());
        linkService.saveLink(link);
        return getUrl();
    }

//TODO: make edit option in popup
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


//    @Autowired
//    PhotoBucketValidator photoBucketValidator;
//
//    @InitBinder("photoBucket")
//    protected void initBinderFileBucket(WebDataBinder binder) {
//        binder.setValidator(photoBucketValidator);
//    }


    /**
     * This method redirect user to upload page.
     * @param model
     * @return
     */
    @RequestMapping(value = "/uploadpage", method = RequestMethod.GET)
    public String getPhotoUpload(ModelMap model) {
        PhotoPhotoBucket photoPhotoBucket = new PhotoPhotoBucket();
        Photo photo = new Photo();
        PhotoBucket photoBucket = new PhotoBucket();
        model.addAttribute("photo", photo);
        model.addAttribute("photoBucket", photoBucket);
        model.addAttribute("photoPhotoBucket", photoPhotoBucket);
        model.addAttribute("currentUserRole", getCurrentRole());
        return "uploadpage";
    }

    /**
     * This method upload files of the user in two different models: as Entity Photo and as PhotoBucket
     * @param photoPhotoBucket
     * @param model
     * @param result
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadpage", method = RequestMethod.POST)
    public String uploadPhoto(PhotoPhotoBucket photoPhotoBucket, ModelMap model, BindingResult result) throws IOException {

        PhotoBucket photoBucket = photoPhotoBucket.getPhotoBucket();
        Photo photo = photoPhotoBucket.getPhoto();
        photo.setUser(getCurrentUser());
        photo.setPhotoPath(getUploadPath() + photoBucket.getMultipartFile().getOriginalFilename());

//        if (result.hasErrors()) {
//            System.out.println("validation errors");
//            return "uploadpage";
//        } else {
        MultipartFile multipartFile = photoBucket.getMultipartFile();
        File checkDirectory = new File(getUploadPath());
        if (!checkDirectory.exists()) {
            boolean makeDirs = false;
            try {
                checkDirectory.mkdirs();
                makeDirs = true;
            } catch (SecurityException se) {
                logger.config("dir creation error in userController" + se);
//                }
            }
        }
        try {
            FileCopyUtils.copy(photoBucket.getMultipartFile().getBytes(), new File(getUploadPath() + photoBucket.getMultipartFile().getOriginalFilename()));
        } catch (IOException e) {
            logger.config("File copy calls problem in userController" + e);
        }
        photoService.savePhoto(photo);
        return "successupload";
    }


    /**
     * Returns curent user.
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
     * This method returns url for refresh the page.
     * @return
     */
    private String getUrl() {
        String url = "redirect:/" + getCurrentRole() + "/links";
        return url;
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

    /**
     * We user this method for identify user files.
     * @return
     */
    private String getUploadPath() {
        String uploadLocation = "/tmp/App/uploads/" + getCurrentUser().getUserLogin() + "/";
        return uploadLocation;
    }
}
