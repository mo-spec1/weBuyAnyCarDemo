package com.weBuyAnyCar.commons;

import java.lang.*;

public class CarDetailsHolder {
    private String reg;
    private String make;
    private String model;
    private String year;

    public CarDetailsHolder (String reg, String make, String model, String year){
        this.reg = reg;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getReg(){
        return reg;
    }

    public String getMake(){
        return make;
    }

    public String getModel(){
        return model;
    }

    public String getYear(){
        return year;
    }

    public String toString(){
        String formatReg = reg + " ";
        String formatMake = make + " ";
        String formatModel = model + " ";
        String formatYear = year + " ";
        return formatReg + formatMake + formatModel + formatYear;
    }
}
