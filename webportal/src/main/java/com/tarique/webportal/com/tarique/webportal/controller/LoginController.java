package com.tarique.webportal.com.tarique.webportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mehnuma on 1/7/2017.
 */
@Controller
public class LoginController {

    private static final String LOGIN_URL="/user/login";

    @RequestMapping("/login")
    public String login() {
        return LOGIN_URL;
    }
}
