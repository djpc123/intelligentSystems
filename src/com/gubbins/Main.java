package com.gubbins;

import com.xeiam.xchart.Chart;
import com.xeiam.xchart.QuickChart;
import com.xeiam.xchart.SwingWrapper;

public class Main {

    public static void main(String[] args) {
        DataReader dataReader = new DataReader();
        double[] xData = dataReader.getX();
        double[] yData = dataReader.getY();

        // Create Chart
        Chart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);

        // Show it
        new SwingWrapper(chart).displayChart();
    }
}
