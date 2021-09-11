package com.ece4880.lab1.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Controller
public class ThermometerController {

    public static final String ACCOUNT_SID = "AC088384fe5a644619e6991093ed038b2f";
    public static final String AUTH_TOKEN = "ccb4ebaaf2ec8cc5f9e09c78defad059";

    // Main method to test text output
    // Uses example code from Twilio quickstart documentation
    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("+14145269642"), // to number
                        new com.twilio.type.PhoneNumber("+14088374565"), // from number
                        "Guess who got the code texting :)")       // message
                .create();

        System.out.println(message.getSid());
    }

    @GetMapping("")
    public String viewHomePage(){
        return "ThermometerView";
    }

}
