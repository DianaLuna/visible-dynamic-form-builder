package com.example.pengxiang.appproject.beans;

import java.io.Serializable;

/**
 * Created by pengxiang on 08/05 0005.
 */

public class ResourceInfo implements Serializable {
    private String id;
    private String kind;
    private String use;
    private String time;

    public ResourceInfo(){};

    public ResourceInfo(String id,String kind,String use,String time){
        this.id=id;
        this.kind=kind;
        this.use=use;
        this.time=time;
    }
    public String getId(){
        return id;
    }

    public String getKind(){
        return kind;
    }

    public String getUse(){
        return use;
    }

    public String getTime(){
        return time;
    }

    public void setId(String id){
        this.id=id;
    }

    public void setKind(String kind){
        this.kind=kind;
    }

    public void setUse(String use){
        this.use=use;
    }

    public void setTime(String time){
        this.time=time;
    }
}
