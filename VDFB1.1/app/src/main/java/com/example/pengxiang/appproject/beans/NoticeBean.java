package com.example.pengxiang.appproject.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pengxiang on 07/27 0027.
 */

public class NoticeBean implements Serializable{
    private int id;
    private String title;
    private String type;
    private String content;
    private String date;

    public NoticeBean(){

    }
    public NoticeBean(int id,String title,String type,String content,String date){
        this.id = id;
        this.title = title;
        this.type = type;
        this.content = content;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }
}
