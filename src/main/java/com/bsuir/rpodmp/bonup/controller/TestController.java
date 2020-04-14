package com.bsuir.rpodmp.bonup.controller;

import com.bsuir.rpodmp.bonup.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "hahah";
    }

    @GetMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/currentUser")
    @ResponseBody
    public String getCurrentUser() {
return userService.getCurrentUserMail();
    }
}
