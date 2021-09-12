package com.ece4880.lab1.demo;

public class WeatherAttributes {
    private String phoneNumberString = "xxx-xxx-xxxx";
    private long phoneNumber = 0;
    private int maxTemp = 50;
    private int minTemp = 10;

    public String getPhoneNumberString() {
        return phoneNumberString;
    }

    public void setPhoneNumberString(String phoneNumberString) {
        this.phoneNumberString = phoneNumberString;
    }

    public long getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String s){
        int group1 = Integer.parseInt(s.substring(0,3));
        int group2 = Integer.parseInt(s.substring(4,7));
        int group3 = Integer.parseInt(s.substring(8,12));
        this.phoneNumber = group1 * 10000000L +  group2 * 10000L  + group3;
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
}
