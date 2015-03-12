package com.gubbins;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DataReader {

    // Make this a singleton
    private static DataReader instance = new DataReader();

    private ArrayList<Double> x = new ArrayList<>();
    private ArrayList<Double> y = new ArrayList<>();

    private DataReader() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Class.class.getResourceAsStream("/data.csv")))) {
//        try (BufferedReader reader = new BufferedReader(new FileReader("data.csv"))) {
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

    public static ArrayList<Double> getX() {
        return instance.x;
    }

    public static ArrayList<Double> getY() {
        return instance.y;
    }

}
