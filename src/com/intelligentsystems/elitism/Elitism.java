package com.intelligentsystems.elitism;

import com.intelligentsystems.Function;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Elitism {

    public static ArrayList<Function> selectElite(final int numberOfElite, final ArrayList<Function> population) {
        if(numberOfElite > population.size()) throw new IllegalArgumentException("Number of elite required can not be greater than given population size");
        ArrayList<Function> elite = new ArrayList<>();

        population.sort(new Comparator<Function>() {
            @Override
            public int compare(Function o1, Function o2) {
                double f1 = o1.getFitness();
                double f2 = o2.getFitness();
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
