package com.ece4880.lab1.demo;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Table(name = "button")
public class Button {

    @Id
    @Column(nullable = false)
    private Long id;

    @Column
    private String screen_bool;

    @Column(length = 45)
    private String datetime;

    public Button(Long id, String screen_bool, String datetime) {
        this.id = id;
        this.screen_bool = screen_bool;
        this.datetime = datetime;
    }

    public Button() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScreenBool() {
        return screen_bool;
    }

    public void setScreenBool(String screenBool) {
        this.screen_bool = screenBool;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}



