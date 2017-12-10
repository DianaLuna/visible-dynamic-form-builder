package com.example.pengxiang.appproject.beans;

import java.io.Serializable;

/**
 * Created by pengxiang on 07/31 0031.
 */

public class CompanyInfo implements Serializable{
    private String id;
    private String name;
    private String introdoce;

    public CompanyInfo(){}

    public CompanyInfo(String id,String name,String introdoce){
        this.id = id;
        this.name = name;
        this.introdoce = introdoce;
    }

    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getIntrodoce(){
        return introdoce;
    }

    public void setId(String id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setIntrodoce(String introdoce){
        this.introdoce = introdoce;
    }
}
