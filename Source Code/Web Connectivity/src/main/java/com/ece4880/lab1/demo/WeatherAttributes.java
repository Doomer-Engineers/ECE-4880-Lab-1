package com.ece4880.lab1.demo;

public class WeatherAttributes {
    private String phoneNumberString = "";
    private String maxTempString = "Maximum temperature bound reached";
    private String minTempString = "Minimum temperature bound reached";
    private String phoneNumber = "712-216-1659";
    private int maxTemp = 50;
    private int minTemp = 10;

    public String getPhoneNumberString() {
        return phoneNumberString;
    }

    public void setPhoneNumberString(String phoneNumberString) {
        this.phoneNumberString = phoneNumberString;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String s){
        this.phoneNumber=s.replace("-", "");
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTempString() {
        return maxTempString;
    }

    public void setMaxTempString(String maxTempString) {
        this.maxTempString = maxTempString;
    }

    public String getMinTempString() {
        return minTempString;
    }

    public void setMinTempString(String minTempString) {
        this.minTempString = minTempString;
    }
}
