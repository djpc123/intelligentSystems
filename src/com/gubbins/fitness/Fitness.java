package com.gubbins.fitness;

import java.util.ArrayList;
import java.util.Iterator;

public class Fitness {

    /**
     * Calculates the fitness as the distance between the two lines.
     * @param yData The arraylist of values calculated.
     * @param target The arraylist of the target values.
     * @return The fitness value.
     */
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
