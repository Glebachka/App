package com.saienko.testcontroller;


import com.saienko.config.TestContext;
import com.saienko.controller.AppController;
import com.saienko.model.*;
import com.saienko.service.userService.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.support.BindingAwareModelMap;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


/**
 * Created by gleb on 10.12.2015.
 */
@ContextConfiguration(classes = {TestContext.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AppControllerTest {


    private UserService userServiceMock;

    private AppController appController;

    @Resource
    private Validator validator;

    private MessageSource messageSourceMock;


    @Before
    public void setUp(){
        appController = new AppController();

        messageSourceMock = mock(MessageSource.class);
        ReflectionTestUtils.setField(appController,"messageSource", messageSourceMock);

        userServiceMock = mock(UserService.class);
        ReflectionTestUtils.setField(appController, "userService", userServiceMock);
    }

//    @Before
    private User mockUser(){


        Link linkTwo = mock(Link.class);
        Link linkOne = new Link();
        linkOne.setLink("linkLink");
        linkOne.setUser(null);
        linkOne.setLinkDescription("descriptionLink");
        linkOne.setLinkId(3);
        Set<Link> linkSet = new HashSet<Link>();
        linkSet.add(linkOne);

        Photo photo = new Photo();
        photo.setPhotoPath("path");
        photo.setUser(null);
        photo.setPhotoAvatar(false);
        photo.setPhotoDescription("descriptionPhoto");
        photo.setPhotoId(55);
        Set<Photo> photoSet = new HashSet<Photo>();
        photoSet.add(photo);

        Bank bank = new Bank();
        bank.setBankName("bankName");
        bank.setBankId(44);
        Set<Bank> bankSet = new HashSet<Bank>();
        bankSet.add(bank);

        UserRole role = new UserRole();
        role.setRole("USER");
        Set<UserRole> userRoleSet = new HashSet<UserRole>();
        userRoleSet.add(role);

        User user = new User();
        user.setUserId((4));
        user.setUserName("name");
        user.setLinks(linkSet);
        user.setUserLogin("login");
        user.setPhotos(photoSet);
        user.setUserPassword("pass");
        user.setBanks((bankSet));
        user.setUserRoles(userRoleSet);
        return user;
    }


    @Test
    public void listUsers() throws Exception {
        BindingAwareModelMap model = new BindingAwareModelMap();

        List<User> users = new ArrayList<User>();
        when(userServiceMock.findAllUsers()).thenReturn(users);


        String view = appController.listUsers(model);

        verify(userServiceMock, times(1)).findAllUsers();
        verifyNoMoreInteractions(userServiceMock);
        verifyZeroInteractions(messageSourceMock);

//        assertEquals("allusers", view);
        assertEquals(users, model.asMap().get("users"));
    }


    @Test
    public void getBanks(){

    }
}
