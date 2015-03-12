package com.gubbins;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Double> xData = DataReader.getX();
        ArrayList<Double> yData = DataReader.getY();

//        GubChart gub = new GubChart("Title", "x", "f(x)");
//        gub.addSeries("f(x)", xData, yData);

        new GeneticAlgorithm(null).run();
    }

}
