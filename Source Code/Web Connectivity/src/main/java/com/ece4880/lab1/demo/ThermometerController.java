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

    public static String to_number = "+14145269642";
    public static final String FROM_NUMBER = "+14088374565";

    //getter and setter for the number to text
    public static String getTo_number() {
        return to_number;
    }
    public static void setTo_number(String to_number) {
        ThermometerController.to_number = to_number;
    }

    // Method that sends a given message to the TO_NUMBER
    // Uses example code from Twilio quickstart documentation
    public static void sendMessage(String my_message){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(to_number),   // to number
                        new com.twilio.type.PhoneNumber(FROM_NUMBER), // from number
                        my_message)                                   // message
                .create();

        System.out.println(message.getSid());
    }

    // Code to send text based on temperature booleans
    public static void textTempLimits(boolean maxTemp, boolean minTemp){
        if(maxTemp){
            sendMessage("Maximum temperature reached");
        }
        else if(minTemp){
            sendMessage("Minimum temperature reached");
        }
    }

    @GetMapping("")
    public String viewHomePage(){
        return "ThermometerView";
    }

    // Main method to test text output
    public static void main(String[] args) {
//        sendMessage("Hello world.");
    }

}
