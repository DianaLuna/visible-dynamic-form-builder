package com.example.pengxiang.appproject.beans;

import java.io.Serializable;

/**
 * Created by pengxiang on 07/31 0031.
 */

public class RecruitInfo implements Serializable{
    private int id;
    private String job;
    private String money;
    private String date;
    private String place;
    private String com_name;
    private String require;
    private String contact;

    public RecruitInfo(){}

    public RecruitInfo(int id,String com_name,String require,String contact,
                       String money,String date,String place,String job){
        this.id = id;
        this.com_name = com_name;
        this.require = require;
        this.contact = contact;
        this.money = money;
        this.date = date;
        this.place = place;
        this.job = job;
    }

    public int getId(){
        return id;
    }
    public String getJob(){
        return job;
    }
    public String getMoney(){
        return money;
    }
    public String getDate(){
        return date;
    }
    public String getPlace(){
        return place;
    }
    public String getCom_name(){
        return com_name;
    }
    public String getRequire(){
        return require;
    }
    public String getContact(){
        return contact;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setJob(String job){
        this.job = job;
    }
    public void setMoney(String money){
        this.money = money;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setPlace(String place){
        this.place = place;
    }
    public void setCom_name(String com_name){
        this.com_name = com_name;
    }
    public void setRequire(String require){
        this.require = require;
    }
    public void setContact(String contact){
        this.contact = contact;
    }
}
