package com.example.pengxiang.appproject.beans;

import java.io.Serializable;

/**
 * Created by pengxiang on 07/31 0031.
 */

public class Faq implements Serializable{
    private String question;
    private String answer;

    public Faq(){

    }
    public Faq(String question,String answer){
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion(){
        return question;
    }
    public String getAnswer(){
        return answer;
    }
    public void setQuestion(String question){
        this.question = question;
    }
    public void setAnswer(String answer){
        this.answer = answer;
    }
}
