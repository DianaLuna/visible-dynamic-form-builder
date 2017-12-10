package com.example.pengxiang.appproject.beans;

import java.io.Serializable;

/**
 * Created by pengxiang on 08/05 0005.
 */

public class ConnectionInfo implements Serializable {
    private String name;
    private String mail;
    private String phone;

    public  ConnectionInfo(){}

    public ConnectionInfo(String name,String mail,String phone){
        this.name=name;
        this.mail=mail;
        this.phone=phone;
    }

    public String getName(){
        return name;
    }

    public String getMail(){
        return mail;
    }

    public String getPhone(){
        return phone;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setmail(String mail){
        this.mail=mail;
    }

    public void setPhone(String phone){
        this.phone=phone;
    }

}
