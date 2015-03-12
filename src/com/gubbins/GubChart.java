package com.gubbins;

import com.xeiam.xchart.*;

import java.io.IOException;
import java.util.ArrayList;

public class GubChart {

    private Chart chart;
    private XChartPanel xChart;
    private ArrayList<String> seriesNames;

    public GubChart(String chartTitle, String xAxisLabel, String yAxisLabel) {
        seriesNames = new ArrayList<String>();
        chart = new ChartBuilder().chartType(StyleManager.ChartType.Line).width(800).height(600).title(chartTitle).xAxisTitle(xAxisLabel).yAxisTitle(yAxisLabel).build();
//        xChart = new XChartPanel(chart);
//        JFrame f = new JFrame();
//        f.setLayout(new BorderLayout());
//        f.add(xChart, BorderLayout.CENTER);
//        f.pack();
//        f.setVisible(true);
    }

    public void setLog() {
        chart.getStyleManager().setYAxisLogarithmic(true);
    }

    public void save(String path) {
        try {
            BitmapEncoder.saveBitmap(chart, path, BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
