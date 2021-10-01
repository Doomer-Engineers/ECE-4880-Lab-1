package com.ece4880.lab1.demo;

import javax.persistence.*;

@Entity
@Table(name = "temperature")
public class Temperature {
    @Id
    @Column(nullable = false, unique = true)
    private Long id;

    @Column
    private Float temp;

    @Column(length = 45)
    private String date;

    @Column
    private int probe;

    public Temperature(Long id, Float temp, String date,  int probe) {
        this.id = id;
        this.temp = temp;
        this.date = date;
        this.probe = probe;
    }

    public Temperature() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTemp() {
        return temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProbe() {
        return probe;
    }

    public void setProbe(int probe) {
        this.probe = probe;
    }
}
