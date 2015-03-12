package com.intelligentsystems;

import com.xeiam.xchart.*;

import java.io.IOException;
import java.util.ArrayList;

public class Chart {

    private com.xeiam.xchart.Chart chart;
    private ArrayList<String> seriesNames;

    public Chart(String chartTitle, String xAxisLabel, String yAxisLabel) {
        seriesNames = new ArrayList<>();
        chart = new ChartBuilder().chartType(StyleManager.ChartType.Line).width(800).height(600).title(chartTitle).xAxisTitle(xAxisLabel).yAxisTitle(yAxisLabel).build();
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
        chart.addSeries(seriesName, xData, yData).setMarker(SeriesMarker.NONE);
        seriesNames.add(seriesName);
    }

    public void addSeries(String title,String seriesName, ArrayList<? extends Number> xData, ArrayList<? extends Number> yData) {
        addSeries(seriesName, xData, yData);
        chart.setChartTitle(title);
    }

}
