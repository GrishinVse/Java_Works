package com.tempconv;

import com.interfaces.Printable;

public class Converter {
    Printer printer;
    private String measure;

    public Converter(String measure){
        this.measure = measure;
    }

    public Double toConvert(String toMeasure, double temp){
        if ((this.measure.toLowerCase() == "cels") && (toMeasure == "kelv")){
            return temp + 273.15;
        } else if ((this.measure.toLowerCase() == "cels") && (toMeasure == "far")){
            return (9/5 * temp) + 32;
        } else if ((this.measure.toLowerCase() == "kelv") && (toMeasure == "cels")){
            return temp - 273.15;
        } else if ((this.measure.toLowerCase() == "far") && (toMeasure == "cels")){
            return 5/9*(temp - 32);
        } else {
            return 0.0;
        }
    }

//    public void print(String toMeasure, double temp){
//        System.out.printf("%f in %s => %f",temp, toMeasure, toConvert(toMeasure, temp) );
//    };

    public void print(Printer printer){
        System.out.printf("%f in %s => %f",temp, toMeasure, toConvert(toMeasure, temp) );
    };

}
