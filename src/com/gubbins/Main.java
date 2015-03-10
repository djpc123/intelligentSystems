package com.gubbins;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        DataReader dataReader = new DataReader();
        ArrayList<Double> xData = dataReader.getX();
        ArrayList<Double> yData = dataReader.getY();

        GubChart gub = new GubChart("Title", "x", "f(x)");
        gub.addSeries("f(x)", xData, yData);

        new GeneticAlgorithm(gub).run();
    }

}
