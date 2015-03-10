package com.gubbins;

import com.gubbins.crossover.Crossover;
import com.gubbins.crossover.SingleSplitCrossover;
import com.gubbins.elitism.Elitism;
import com.gubbins.mutation.Mutator;
import com.gubbins.mutation.RandomMutator;
import com.gubbins.tornament.Tournament;

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm {

    private Random randomFunction = new Random();
    private Random randomModify = new Random();
    private Mutator mutator = new RandomMutator();
    private Crossover crossover = new SingleSplitCrossover();
    private Tournament tournament = new Tournament();
    private ArrayList<Function> initialPopulation = new ArrayList<>();
    private ArrayList<Function> currentPopulation = new ArrayList<>();
    private GubChart chart;
    private int populationSize = 10000;
    private int eliteNumber = (populationSize/100)*10;
    private int randomNumber = (populationSize/100)*5;

    public GeneticAlgorithm(GubChart gub) {
        chart = gub;
    }

    public void run() {
        generateInitialPopulation(populationSize*2);

        for(int j=0; j<1000000; j++) {
            currentPopulation.addAll(Elitism.selectElite(eliteNumber, initialPopulation));
            Function winner = tournament.runTournament(initialPopulation);
            while (currentPopulation.size() < populationSize - randomNumber) {
                int chance = randomModify.nextInt(100) + 1;
                if (chance <= 5) currentPopulation.add(mutator.mutate(winner));
                else currentPopulation.addAll(crossover.crossover(winner, tournament.runTournament(initialPopulation)));
            }
            for (int i = currentPopulation.size(); i < populationSize; i++) {
                currentPopulation.add(generateRandomFunction());
            }

            initialPopulation.clear();
            initialPopulation.addAll(currentPopulation);
            currentPopulation.clear();

            Function best = Elitism.selectElite(1, initialPopulation).get(0);
            System.out.println(j + " " + best.getFitness());
            chart.addSeries(best.toString(), "best",DataReader.getX(), best.calculate(DataReader.getX()));
        }

    }

    private Function generateRandomFunction() {
        return new Function(generateVariable(),
                generateVariable(),
                generateVariable(),
                generateVariable(),
                generateVariable(),
                generateVariable());
    }

    private double generateVariable() {
        return randomFunction.nextInt(1000) + randomFunction.nextDouble();
    }

    private void generateInitialPopulation(int populationSize) {
        for(int i=0; i<populationSize; i++) {
            initialPopulation.add(generateRandomFunction());
        }
    }

}
