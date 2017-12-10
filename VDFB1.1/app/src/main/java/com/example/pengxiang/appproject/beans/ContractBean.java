package com.example.pengxiang.appproject.beans;

import java.io.Serializable;

/**
 * Created by pengxiang on 08/06 0006.
 */

public class ContractBean implements Serializable {

    private int id;
    private String name;//合同名称
    private String content;//合同内容
    private String sDate;//生效时间
    private String eDate;//到期时间

    public ContractBean(){}
    public ContractBean(int id,String name,String content,String sDate,String eDate){
        this.id=id;
        this.name=name;
        this.content=content;
        this.sDate=sDate;
        this.eDate=eDate;
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

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content=content;
    }

    public String getsDate(){
        return sDate;
    }
    public void setsDate(String sDate){
        this.sDate=sDate;
    }

    public String geteDate(){
        return eDate;
    }
    public void seteDate(String eDate){
        this.eDate=eDate;
    }

}
