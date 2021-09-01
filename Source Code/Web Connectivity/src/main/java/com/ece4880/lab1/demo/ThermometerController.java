package com.ece4880.lab1.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThermometerController {

    @GetMapping("")
    public String viewHomePage(){
        return "HTML/ThermometerView";
    }
}
