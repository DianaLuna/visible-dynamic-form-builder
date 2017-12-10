package com.example.pengxiang.appproject.beans;

import java.io.Serializable;

/**
 * Created by pengxiang on 08/06 0006.
 */

public class MaintenanceBean implements Serializable {

    private int id;
    private String name;//报修人名字
    private String address;//报修地址
    private String phone;
    private String date;
    private String time;
    private String detail;//详细内容

    public MaintenanceBean(int id,String name,String address,String phone,String date,String time,String detail){
        this.id=id;
        this.name=name;
        this.address=address;
        this.phone=phone;
        this.date=date;
        this.time=time;
        this.detail=detail;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }

    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
    }

    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time=time;
    }

    public String getDetail(){
        return detail;
    }
    public void setString(String detail){
        this.detail=detail;
    }


}