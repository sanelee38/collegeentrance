package com.sanelee.collegeentrance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SchoolController {
    @GetMapping("/school")
    public String school(){
        return "school";
    }
}
