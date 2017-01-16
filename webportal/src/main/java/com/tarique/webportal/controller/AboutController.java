package com.tarique.webportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mehnuma on 12/28/2016.
 */
@Controller
public class AboutController {
    @RequestMapping("/about")
    public String about() {
        return "copy/about";
    }
}
