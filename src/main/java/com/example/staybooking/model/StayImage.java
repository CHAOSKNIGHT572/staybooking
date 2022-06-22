//package com.example.staybooking.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.*;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import java.io.Serializable;
//
//@Entity
//@Table(name = "stay_image")
//
//public class StayImage implements Serializable {
//    private static final long serialVersionUID = 1L;
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public Stay getStay() {
//        return stay;
//    }
//
//    public void setStay(Stay stay) {
//        this.stay = stay;
//    }
//
//    @Id
//    private String url;
//
//    @ManyToOne
//    @JoinColumn(name = "stay_id")
//    @JsonIgnore
//    private Stay stay;
//
//    public StayImage() {
//
//    }
//
//    public StayImage(String url, Stay stay) {
//        this.url = url;
//        this.stay = stay;
//    }
//}
