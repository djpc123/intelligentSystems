package com.gubbins.mutation;

import com.gubbins.Function;

import java.util.Random;

public class RandomMutator implements Mutator {

    private Random random = new Random();

    @Override
    public Function mutate(Function f) {
        double[] genome = f.getGenome();

        int mutationIndex = random.nextInt(genome.length);
        int i = random.nextInt(2);

        if (i > 0) {
            genome[mutationIndex] = genome[mutationIndex] * 1.1;
        } else {
            genome[mutationIndex] = genome[mutationIndex] * 0.9;
        }

        return new Function(genome[0],genome[1],genome[2],genome[3],genome[4],genome[5]);

    }
}
