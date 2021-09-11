package com.ece4880.lab1.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Controller
public class ThermometerController {

    @GetMapping("")
    public String viewHomePage(){
        return "ThermometerView";
    }

    //Test comment
}
