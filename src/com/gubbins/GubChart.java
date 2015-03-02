package com.gubbins;

import com.xeiam.xchart.*;

import javax.swing.*;
import java.awt.*;
import java.lang.Number;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Sam on 27/02/2015.
 */
public class GubChart {

    private Chart chart;
    private XChartPanel xChart;
    private ArrayList<String> seriesNames;

    public GubChart(String chartTitle, String xAxisLabel, String yAxisLabel) {
        seriesNames = new ArrayList<String>();
        chart = new ChartBuilder().chartType(StyleManager.ChartType.Line).width(800).height(600).title(chartTitle).xAxisTitle(xAxisLabel).yAxisTitle(yAxisLabel).build();
        chart.getStyleManager();
        xChart = new XChartPanel(chart);
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.add(xChart, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
    }

    public void addSeries(String seriesName, ArrayList<? extends Number> xData, ArrayList<? extends Number> yData) {
        if(seriesNames.contains(seriesName)) {
            //already added, update instead
            xChart.updateSeries(seriesName, xData, yData).setMarker(SeriesMarker.NONE);
        }
        else {
            chart.addSeries(seriesName, xData, yData).setMarker(SeriesMarker.NONE);
            seriesNames.add(seriesName);
        }
    }

    public void addSeries(String title,String seriesName, ArrayList<? extends Number> xData, ArrayList<? extends Number> yData) {
        addSeries(seriesName, xData, yData);
        chart.setChartTitle(title);
    }

}
