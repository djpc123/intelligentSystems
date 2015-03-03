package com.gubbins;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Sam on 03/03/2015.
 */
public class Fitness {

    public static double calculateFitness(ArrayList<Double> yData, ArrayList<Double> target) {
        Iterator<Double> yIt = yData.iterator();
        Iterator<Double> tarIt = target.iterator();
        double fitness = 0;
        while(yIt.hasNext() && tarIt.hasNext()) {
            double targetDouble = tarIt.next();
            double yDouble = yIt.next();
            fitness += Math.pow(targetDouble-yDouble,2);
        }
        return Math.sqrt(fitness);
    }

}
