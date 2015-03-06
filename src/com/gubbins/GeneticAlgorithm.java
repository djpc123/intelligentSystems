package com.gubbins;

import com.gubbins.crossover.Crossover;
import com.gubbins.elitism.Elitism;
import com.gubbins.fitness.Fitness;
import com.gubbins.mutation.Mutator;
import com.gubbins.tornament.Tournament;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Sam on 04/03/2015.
 */
public class GeneticAlgorithm {

    private Random randomFunction;
    private Fitness fitness;
    private Mutator mutator;
    private Crossover crossover;
    private Tournament tournament;
    private Elitism elitism;
    private ArrayList<Function> initialPopulation;
    private ArrayList<Function> currentPopulation;

    public GeneticAlgorithm() {
        randomFunction = new Random();
        fitness = new Fitness() {};
        mutator = new Mutator() {};
        crossover = new Crossover() {};
        tournament = new Tournament() {};
        elitism = new Elitism() {};
        initialPopulation = new ArrayList<>();
        currentPopulation = new ArrayList<>();
    }

    public void run() {
        generateInitialPopulation(100);
        elitism.selectElite(fitness, 5, initialPopulation);
    }

    private Function generateRandomFunction() {
        return new Function(randomFunction.nextDouble(),randomFunction.nextDouble(),randomFunction.nextDouble(),
                randomFunction.nextDouble(),randomFunction.nextDouble(),randomFunction.nextDouble());
    }

    private void generateInitialPopulation(int populationSize) {
        for(int i=0; i<populationSize; i++) {
            initialPopulation.add(generateRandomFunction());
        }
    }

}
