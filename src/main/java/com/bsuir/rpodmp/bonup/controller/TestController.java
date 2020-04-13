package com.bsuir.rpodmp.bonup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
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
}
