package com.gubbins.crossover;

import com.gubbins.Function;

import java.util.ArrayList;
import java.util.Random;

public class SingleSplitCrossover implements Crossover {

    private Random random = new Random();

    @Override
    public ArrayList<Function> crossover(Function f1, Function f2) {
        int splitPoint = getSplitPoint();

        double[] child1 = new double[6];
        double[] child2 = new double[6];
        final double[] parent1 = f1.getGenome();
        final double[] parent2 = f2.getGenome();

        int i;
        for (i = 0; i < splitPoint; i++) {
            child1[i] = parent1[i];
            child2[i] = parent2[i];
        }
        for (; i <= 6; i++) {
            child1[i] = parent2[i];
            child2[i] = parent1[i];
        }

        final ArrayList<Function> children = new ArrayList<>();
        children.add(new Function(child1[0],child1[1],child1[2],child1[3],child1[4],child1[5]));
        children.add(new Function(child2[0],child2[1],child2[2],child2[3],child2[4],child2[5]));

        return children;
    }

    /**
     * @return An integer between 1 and 5.
     */
    private int getSplitPoint() {
        return (random.nextInt(5) + 1);
    }
}
