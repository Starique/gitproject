package com.tarique.webportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mehnuma on 1/7/2017.
 */
@Controller
public class PayloadController {
    private static final String PAYLOAD_VIEW_NAME = "payload/payload";

    @RequestMapping("/payload")
    public String payload() {
        return PAYLOAD_VIEW_NAME;
    }
}
