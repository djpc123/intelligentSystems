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

    public Double[] getX() {
        return x.toArray(new Double[x.size()]);
    }

    public Double[] getY() {
        return y.toArray(new Double[y.size()]);
    }
}
