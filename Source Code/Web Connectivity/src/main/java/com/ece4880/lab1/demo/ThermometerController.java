package com.ece4880.lab1.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ThermometerController {

    static final String HOMEPAGE = "ThermometerView";
    static final String WEATHER_ATTRIBUTES = "weatherAttributes";

    @ModelAttribute(WEATHER_ATTRIBUTES)
    public WeatherAttributes weatherAttributesDTO() {
        return new WeatherAttributes();
    }

    @GetMapping("")
    public String viewHomePage(@ModelAttribute(WEATHER_ATTRIBUTES) WeatherAttributes weatherAttributes, Model model){
        model.addAttribute(weatherAttributes);
        return HOMEPAGE;
    }

    @PostMapping(value ="")
    public String updateValues(@ModelAttribute(WEATHER_ATTRIBUTES) WeatherAttributes weatherAttributes, Model model){
        model.addAttribute(weatherAttributes);
        weatherAttributes.setPhoneNumber(weatherAttributes.getPhoneNumberString());
        System.out.println(weatherAttributes.getPhoneNumber());
        System.out.println(weatherAttributes.getMaxTemp());
        System.out.println(weatherAttributes.getMinTemp());
        return HOMEPAGE;
    }

}
