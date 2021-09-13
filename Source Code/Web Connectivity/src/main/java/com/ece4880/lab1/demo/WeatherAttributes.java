package com.ece4880.lab1.demo;

public class WeatherAttributes {
    static final String FAHRENHEIT = "Fahrenheit";
    static final String CELSIUS = "Celsius";

    private String phoneNumberString = "e.g. xxx-xxx-xxxx";
    private String degrees = CELSIUS;
    private String previousDegrees = "";
    private long phoneNumber = 0;
    private int maxTemp = 50;
    private int minTemp = 10;

    public String getPhoneNumberString() {
        return phoneNumberString;
    }

    public void setPhoneNumberString(String phoneNumberString) {
        this.phoneNumberString = phoneNumberString;
    }

    public String getDegrees(){ return degrees;}

    public void setDegrees(String degrees) {this.degrees = degrees;}

    public String getPreviousDegrees() {return previousDegrees;}

    public void setPreviousDegrees(String previous){this.previousDegrees = previous;}

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

    public void updateTemps(){
        if(degrees.equals(CELSIUS) && previousDegrees.equals(FAHRENHEIT)){
            setMaxTemp(convertToCelsius(getMaxTemp()));
            setMinTemp(convertToCelsius(getMinTemp()));
            setPreviousDegrees(CELSIUS);
        }
        else if( degrees.equals(FAHRENHEIT) && previousDegrees.equals(CELSIUS) ||
                   degrees.equals(FAHRENHEIT) && previousDegrees.equals("")){
            setMaxTemp(convertToFahrenheit(getMaxTemp()));
            setMinTemp(convertToFahrenheit(getMinTemp()));
            setPreviousDegrees(FAHRENHEIT);
        }

        //logic for model because JS is not being implemented for on change for the degree drop down A.K.A need to fix later
        if(getDegrees().equals(CELSIUS) && getMaxTemp() > 50){
            setMaxTemp(50);
        }
        else if(getDegrees().equals(FAHRENHEIT) && getMaxTemp() < 50){
            setMaxTemp(122);
        }

        if (getDegrees().equals(CELSIUS) && getMinTemp() > 50){
            setMinTemp(10);
        }
        else if(getDegrees().equals(FAHRENHEIT) && getMinTemp() < 50){
            setMinTemp(50);
        }
    }

    private int convertToFahrenheit(int temp){
        double tmp = 9.0/5.0 * (double) temp + 32;
        return (int) Math.floor(tmp);
    }

    private int convertToCelsius(int temp){
        double tmp = 5.0/9.0 * (temp - 32);
        return (int) Math.floor(tmp);
    }
}
