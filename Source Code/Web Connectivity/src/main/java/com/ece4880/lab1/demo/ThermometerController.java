package com.ece4880.lab1.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Controller
public class ThermometerController {

    private static final String HOMEPAGE = "ThermometerView";
    private static final String WEATHER_ATTRIBUTES = "weatherAttributes";

    private static final String ACCOUNT_SID = "AC088384fe5a644619e6991093ed038b2f";
    private static final String AUTH_TOKEN = "ccb4ebaaf2ec8cc5f9e09c78defad059";

    private static final String FROM_NUMBER = "+14088374565";

    @ModelAttribute(WEATHER_ATTRIBUTES)
    public WeatherAttributes weatherAttributesDTO() {
        return new WeatherAttributes(); }

    @GetMapping("")
    public String viewHomePage(@ModelAttribute(WEATHER_ATTRIBUTES) WeatherAttributes weatherAttributes, Model model){
        model.addAttribute(weatherAttributes);
        return HOMEPAGE;
    }

    @PostMapping("")
    public String updateValues(@ModelAttribute(WEATHER_ATTRIBUTES) WeatherAttributes weatherAttributes, Model model){
        weatherAttributes.setPhoneNumber(weatherAttributes.getPhoneNumberString());
        weatherAttributes.updateTemps();

        //We will be using this logic when we can get data from probe
        /*
        String toPhoneNumber = convertToPhoneNumFormat(weatherAttributes.getPhoneNumber());

        boolean isMaxTemp = false;
        boolean isMinTemp = false;

        //update with data from probe
        int temp = 50;

        if (temp >= weatherAttributes.getMaxTemp()){
            isMaxTemp = true;
        }

        if (temp <= weatherAttributes.getMinTemp()){
            isMinTemp = true;
        }

        textTempLimits(isMaxTemp,isMinTemp, toPhoneNumber);
         */

        System.out.println();
        System.out.println("Phone Number: " + weatherAttributes.getPhoneNumber());
        System.out.println("Max Temp: " + weatherAttributes.getMaxTemp());
        System.out.println("Min Temp: " + weatherAttributes.getMinTemp());
        System.out.println("Current State: " + weatherAttributes.getDegrees());
        model.addAttribute(weatherAttributes);
        return HOMEPAGE;
    }

    // Method that sends a given message to the TO_NUMBER
    // Uses example code from Twilio quickstart documentation
    public static void sendMessage(String myMessage ,String toPhoneNumber){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(toPhoneNumber),   // to number
                        new com.twilio.type.PhoneNumber(FROM_NUMBER), // from number
                        myMessage)                                   // message
                .create();

        System.out.println(message.getSid());
    }

    // Code to send text based on temperature booleans
    public static void textTempLimits(boolean isMaxTemp, boolean isMinTemp, String toPhoneNumber){
        if(isMaxTemp && !isMinTemp){
            sendMessage("Maximum temperature reached", toPhoneNumber);
        }
        else if(isMinTemp && !isMaxTemp){
            sendMessage("Minimum temperature reached", toPhoneNumber);
        }
        else if(isMaxTemp && isMinTemp){
            sendMessage("Temperature reached", toPhoneNumber);
        }
    }

    public static String convertToPhoneNumFormat(long phoneNumber){
        StringBuilder tmp = new StringBuilder(Long.toString(phoneNumber));
        if(Long.toString(phoneNumber).length() < 10){
            for(int i = Long.toString(phoneNumber).length(); i == 10; i++){
                tmp.insert(0, 0);
            }
        }
        return "+1" + tmp;
    }

}
