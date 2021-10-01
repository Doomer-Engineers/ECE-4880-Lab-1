package com.ece4880.lab1.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ThermometerController {

    private static final String HOMEPAGE = "ThermometerView";
    private static final String WEATHER_ATTRIBUTES = "weatherAttributes";
    private static final String BUTTON = "button";
    private static final String TEMPERATURE = "temperature";

    private static final String ACCOUNT_SID = "AC088384fe5a644619e6991093ed038b2f";
    private static final String AUTH_TOKEN = "ccb4ebaaf2ec8cc5f9e09c78defad059";

    private static final String FROM_NUMBER = "+14088374565";

    @Autowired
    private ButtonRepository bRepo;

    @Autowired
    private TemperatureRepository tRepo;

    @ModelAttribute(WEATHER_ATTRIBUTES)
    public WeatherAttributes weatherAttributesDTO() {
        return new WeatherAttributes(); }

    @ModelAttribute(BUTTON)
    public Button buttonDTO() {
        return new Button();
    }

    @ModelAttribute(TEMPERATURE)
    public Temperature temperatureDTO() {
        return new Temperature();
    }

    @GetMapping("")
    public String viewHomePage(@ModelAttribute(WEATHER_ATTRIBUTES) WeatherAttributes weatherAttributes, @ModelAttribute(BUTTON) Button button,  Model model){
        //We will be using this logic when we can get data from probe
        model.addAttribute(weatherAttributes);
        button.setScreenBool("FALSE");
        String toPhoneNumber = weatherAttributes.getPhoneNumber();

        List<Temperature> temps = tRepo.findAll();
        ArrayList<Temperature> temps2 = new ArrayList<>();
        for(int i = temps.size() - 1; i > temps.size() - 300; i--){
            temps2.add(temps.get(i));
        }
        Temperature temp = temps2.get(0);

        model.addAttribute("tempList",temps2);
        model.addAttribute("temp" , temp.getTemp());
        model.addAttribute("probe", temp.getProbe());

        if(temp.getProbe() == 1 && temp.getTemp() == null){
            model.addAttribute("error", "error occurred");
        }
            model.addAttribute(weatherAttributes);
            model.addAttribute(button);
            checkTemps(weatherAttributes, toPhoneNumber, temp);

        //should get new temperature, not just the entire object so filter here
        return HOMEPAGE;
    }

    @PostMapping("")
    public String updateValues(@ModelAttribute(WEATHER_ATTRIBUTES) WeatherAttributes weatherAttributes, @ModelAttribute(BUTTON) Button button, Model model){
        weatherAttributes.setPhoneNumber(weatherAttributes.getPhoneNumberString());


        List<Temperature> temps = tRepo.findAll();
        ArrayList<Temperature> temps2 = new ArrayList<>();
        for(int i = temps.size() - 1; i > temps.size() - 300; i--){
            temps2.add(temps.get(i));
        }
        Temperature temp = temps2.get(0);
        model.addAttribute("tempList",temps2);
        model.addAttribute("temp" , temp.getTemp());
        model.addAttribute("probe", temp.getProbe());

        if(temp.getProbe() == 1 && temp.getTemp() == null){
            model.addAttribute("error", "error occurred");
        }
            model.addAttribute(weatherAttributes);
            model.addAttribute(button);
            String toPhoneNumber = weatherAttributes.getPhoneNumber();
            checkTemps(weatherAttributes, toPhoneNumber, temp);

        System.out.println();
        System.out.println("Phone Number: " + weatherAttributes.getPhoneNumber());
        System.out.println("Max Temp: " + weatherAttributes.getMaxTemp());
        System.out.println("Max Temp Message: " + weatherAttributes.getMaxTempString());
        System.out.println("Min Temp: " + weatherAttributes.getMinTemp());
        System.out.println("Min Temp Message: " + weatherAttributes.getMinTempString());
        System.out.println("Degrees State: " + weatherAttributes.getDegreeState());

        if(button.getScreenBool() !=null) {
            List<Button> buttons = bRepo.findAll();
            Button button2 = new Button();
            button2.setId(buttons.get(buttons.size() - 1).getId() + 1);
            button2.setScreenBool(button.getScreenBool().toUpperCase());
            bRepo.save(button2);
        }
       
        return HOMEPAGE;
    }

    private void checkTemps(@ModelAttribute(WEATHER_ATTRIBUTES) WeatherAttributes weatherAttributes, String toPhoneNumber, Temperature temp) {
        if (temp.getTemp() >= weatherAttributes.getMaxTemp()){
            String message = weatherAttributes.getMaxTempString();
            sendMessage(message, toPhoneNumber);
            System.out.println("Sending text message to: " + toPhoneNumber + ", Maximum boundary for temperature reached.");
        }

        if (temp.getTemp() <= weatherAttributes.getMinTemp()) {
            String message = weatherAttributes.getMinTempString();
            sendMessage(message, toPhoneNumber);
            System.out.println("Sending text message to: " + toPhoneNumber + ", Minimum boundary for temperature reached.");

        }
    }

    public static void sendMessage(String myMessage ,String toPhoneNumber){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(toPhoneNumber),   // to number
                        new com.twilio.type.PhoneNumber(FROM_NUMBER), // from number
                        myMessage)                                   // message
                .create();

        System.out.println(message.getSid());
    }

}
