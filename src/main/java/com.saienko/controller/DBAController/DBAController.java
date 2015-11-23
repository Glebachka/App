package com.saienko.controller.DBAController;

import com.saienko.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gleb on 21.11.2015.
 */
@Controller
@RequestMapping("/dba")
public class DBAController {

    @Autowired
    UserService userService;






}
