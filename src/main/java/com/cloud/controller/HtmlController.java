package com.cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("html")
public class HtmlController {
    @RequestMapping(value = "submit", method = RequestMethod.GET)
    public String submit(String model) {
        return "submit";
    }
}