package com.example.pengxiang.appproject.beans;

import java.io.Serializable;

/**
 * Created by pengxiang on 08/05 0005.
 */

public class ServiceInfo implements Serializable {
    private String title;
    private String content;
    private String time;

    public ServiceInfo(){}

    public ServiceInfo(String title,String content,String time){
        this.title=title;
        this.content=content;
        this.time=time;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public String getTime(){
        return time;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setContent(String content){
        this.content=content;
    }

    public void setTime(String time){
        this.time=time;
    }

}
