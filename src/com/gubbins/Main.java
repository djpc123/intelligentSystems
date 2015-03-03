package com.gubbins;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        DataReader dataReader = new DataReader();
        ArrayList<Double> xData = dataReader.getX();
        ArrayList<Double> yData = dataReader.getY();

        GubChart gub = new GubChart("Title", "x", "f(x)");
        gub.addSeries("f(x)", xData, yData);

        Random r = new Random();
        for(int i=0; i<Integer.MAX_VALUE; i++) {
            Function f = new Function(r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt());
            gub.addSeries(f.toString(), "f(x)", xData, f.calculate(xData));
        }
    }

}
