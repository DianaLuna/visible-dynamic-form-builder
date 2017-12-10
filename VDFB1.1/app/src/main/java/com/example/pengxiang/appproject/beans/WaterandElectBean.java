package com.example.pengxiang.appproject.beans;

import java.io.Serializable;

/**
 * Created by pengxiang on 08/06 0006.
 */

public class WaterandElectBean implements Serializable {
    private int id;
    private String date;
    private int amountWaterUsed;//水用量
    private double moneyWaterLeft;//剩余水费
    private double moneyWaterUsed;//水花费
    private int amountElectUsed;
    private double moneyElectLeft;
    private double moneyElectUsed;

    public WaterandElectBean(){}
    public WaterandElectBean(int id,String date,int amountWaterUsed, double moneyWaterLeft, double moneyWaterUsed,
                             int amountElectUsed,double moneyElectLeft,double moneyElectUsed){
        this.id=id;
        this.date=date;
        this.amountWaterUsed=amountWaterUsed;
        this.moneyWaterLeft=moneyWaterLeft;
        this.moneyWaterUsed=moneyWaterUsed;
        this.amountElectUsed=amountElectUsed;
        this.moneyElectLeft=moneyElectLeft;
        this.moneyElectUsed=moneyElectUsed;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public int getAmountWaterUsed() {
        return amountWaterUsed;
    }
    public void setAmountWaterUsed(int amountWaterUsed) {
        this.amountWaterUsed = amountWaterUsed;
    }

    public double getMoneyWaterLeft() {
        return moneyWaterLeft;
    }
    public void setMoneyWaterLeft(double moneyWaterLeft) {
        this.moneyWaterLeft = moneyWaterLeft;
    }

    public double getMoneyWaterUsed() {
        return moneyWaterUsed;
    }
    public void setMoneyWaterUsed(double moneyWaterUsed) {
        this.moneyWaterUsed = moneyWaterUsed;
    }

    public int getAmountElectUsed() {
        return amountElectUsed;
    }
    public void setAmountElectUsed(int amountElectUsed) {
        this.amountElectUsed = amountElectUsed;
    }

    public double getMoneyElectLeft() {
        return moneyElectLeft;
    }
    public void setMoneyElectLeft(double moneyElectLeft) {
        this.moneyElectLeft = moneyElectLeft;
    }

    public double getMoneyElectUsed() {
        return moneyElectUsed;
    }
    public void setMoneyElectUsed(double moneyElectUsed) {
        this.moneyElectUsed = moneyElectUsed;
    }
}

