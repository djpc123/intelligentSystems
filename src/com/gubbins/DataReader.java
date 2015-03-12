package com.gubbins;

import java.io.*;
import java.util.ArrayList;

public class DataReader {

    // Make this a singleton
    private static DataReader instance = new DataReader();

    private ArrayList<Double> x = new ArrayList<>();
    private ArrayList<Double> y = new ArrayList<>();

    private DataReader() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Class.class.getResourceAsStream("/data.csv")))) {
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
        } catch (IOException | NullPointerException e) {
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
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    public static ArrayList<Double> getX() {
        return instance.x;
    }

    public static ArrayList<Double> getY() {
        return instance.y;
    }

}
