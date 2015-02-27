package com.gubbins;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {

    private ArrayList<Double> x = new ArrayList<>();
    private ArrayList<Double> y = new ArrayList<>();

    public DataReader() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                if (split.length != 2) {
                    throw new IOException("Malformed line: " + line);
                } else {
                    x.add(Double.parseDouble(split[0]));
                    y.add(Double.parseDouble(split[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to read data: " + e.getMessage());
        }
    }

    public double[] getX() {
        double[] xData = new double[x.size()];
        for (int i = 0; i < x.size(); i++) {
            xData[i] = x.get(i);
        }
        return xData;
    }

    public double[] getY() {
        double[] yData = new double[y.size()];
        for (int i = 0; i < y.size(); i++) {
            yData[i] = y.get(i);
        }
        return yData;
    }
}
