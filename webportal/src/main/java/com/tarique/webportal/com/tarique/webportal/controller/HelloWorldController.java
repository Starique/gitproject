package com.tarique.webportal.com.tarique.webportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mehnuma on 12/16/2016.
 */

@Controller
public class HelloWorldController {
    @RequestMapping("/")
    public String sayHello() {
        return "index";
    }
}
