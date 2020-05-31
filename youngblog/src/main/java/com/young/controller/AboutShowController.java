package com.young.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author young
 * @Description
 * @date 2020-05-08 9:41
 */
@Controller
public class AboutShowController {



    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
