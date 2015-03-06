package com.gubbins.elitism;

import com.gubbins.DataReader;
import com.gubbins.Function;
import com.gubbins.fitness.Fitness;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by Sam on 04/03/2015.
 */
public interface Elitism {

    public default ArrayList<Function> selectElite(final Fitness fitness, final int numberOfElite, final ArrayList<Function> population) {
        if(numberOfElite > population.size()) throw new IllegalArgumentException("Number of elite required can not be greater than given population size");
        final DataReader reader = new DataReader();
        ArrayList<Function> elite = new ArrayList<>(); //may be dangerous

        //Sort population with lowest fitness at start
        population.sort(new Comparator<Function>() {
            @Override
            public int compare(Function o1, Function o2) {
                double f1 = fitness.calculateFitness(o1.calculate(reader.getX()), reader.getY());
                double f2 = fitness.calculateFitness(o2.calculate(reader.getX()), reader.getY());
                if (f1 < f2) return -1;
                if (f1 > f2) return 1;
                if (f1 == f2) return 0;
                return 0;
            }
        });
        Iterator<Function> it = population.iterator();
        while(it.hasNext() && elite.size() < numberOfElite) {
            elite.add(it.next());
        }
        return elite;
    }

}
