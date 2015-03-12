package com.intelligentsystems.mutation;

import com.intelligentsystems.Function;

import java.util.Random;

public class RandomMutator implements Mutator {

    private Random random = new Random();

    @Override
    public Function mutate(Function f) {
        double[] genome = f.getGenome();

        int mutationIndex = random.nextInt(genome.length);
        double change = random.nextGaussian();

        genome[mutationIndex] = genome[mutationIndex] * (1 + (change/2));

        return new Function(genome[0],genome[1],genome[2],genome[3],genome[4],genome[5]);

    }
}
