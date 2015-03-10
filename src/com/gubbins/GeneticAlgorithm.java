package com.gubbins;

import com.gubbins.crossover.Crossover;
import com.gubbins.crossover.SingleSplitCrossover;
import com.gubbins.elitism.Elitism;
import com.gubbins.fitness.Fitness;
import com.gubbins.mutation.Mutator;
import com.gubbins.mutation.RandomMutator;
import com.gubbins.tornament.Tournament;

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm {

    private Random randomFunction;
    private Random randomModify;
    private Mutator mutator;
    private Crossover crossover;
    private Tournament tournament;
    private Elitism elitism;
    private ArrayList<Function> initialPopulation;
    private ArrayList<Function> currentPopulation;
    private DataReader reader;
    private GubChart chart;

    public GeneticAlgorithm(GubChart gub) {
        randomFunction = new Random();
        randomModify = new Random();
        mutator = new RandomMutator();
        crossover = new SingleSplitCrossover();
        tournament = new Tournament();
        elitism = new Elitism();
        initialPopulation = new ArrayList<>();
        currentPopulation = new ArrayList<>();
        reader = new DataReader();
        chart = gub;
    }

    public void run() {
        generateInitialPopulation(100);

        for(int j=0; j<1000000; j++) {
            currentPopulation.addAll(elitism.selectElite(5, initialPopulation));
            Function winner = tournament.runTournament(initialPopulation);
            while (currentPopulation.size() < 45) {
                int chance = randomModify.nextInt(100) + 1;
                if (chance <= 5) currentPopulation.add(mutator.mutate(winner));
                else currentPopulation.addAll(crossover.crossover(winner, tournament.runTournament(initialPopulation)));
            }
            for (int i = currentPopulation.size(); i < 50; i++) {
                currentPopulation.add(generateRandomFunction());
            }

            initialPopulation.clear();
            initialPopulation.addAll(currentPopulation);
            currentPopulation.clear();

            Function best = elitism.selectElite(1, initialPopulation).get(0);
            System.out.println(j + " " + best.getFitness());
            chart.addSeries(best.toString(), "best",reader.getX(), best.calculate(reader.getX()));
        }

    }

    private Function generateRandomFunction() {
        return new Function(randomFunction.nextInt(25) + randomFunction.nextDouble(),
                randomFunction.nextInt(25) + randomFunction.nextDouble(),
                randomFunction.nextInt(25) + randomFunction.nextDouble(),
                randomFunction.nextInt(25) + randomFunction.nextDouble(),
                randomFunction.nextInt(25) + randomFunction.nextDouble(),
                randomFunction.nextInt(25) + randomFunction.nextDouble());
    }

    private void generateInitialPopulation(int populationSize) {
        for(int i=0; i<populationSize; i++) {
            initialPopulation.add(generateRandomFunction());
        }
    }

}
