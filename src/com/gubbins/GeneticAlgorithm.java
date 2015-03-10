package com.gubbins;

import com.gubbins.crossover.Crossover;
import com.gubbins.crossover.SingleSplitCrossover;
import com.gubbins.elitism.Elitism;
import com.gubbins.mutation.Mutator;
import com.gubbins.mutation.RandomMutator;
import com.gubbins.tornament.Tournament;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class GeneticAlgorithm {

    private final ExecutorService executor = Executors.newFixedThreadPool(8);
    private Random randomFunction = new Random();
    private Mutator mutator = new RandomMutator();
    private Crossover crossover = new SingleSplitCrossover();
    private Tournament tournament = new Tournament();
    private ArrayList<Function> initialPopulation = new ArrayList<>();
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
            List<Function> currentPopulation = Collections.synchronizedList(new ArrayList<>());
            currentPopulation.addAll(Elitism.selectElite(eliteNumber, initialPopulation));

            for (int i = currentPopulation.size(); i < (populationSize - randomNumber); i+=2) {
                executor.execute(() -> {
                    Function parent1 = tournament.runTournament(initialPopulation);
                    Function parent2 = tournament.runTournament(initialPopulation);
                    int crossoverChance = ThreadLocalRandom.current().nextInt(100) + 1;
                    ArrayList<Function> children = new ArrayList<>();
                    if (crossoverChance <= 50) {
                        children.addAll(crossover.crossover(parent1, parent2));
                    } else {
                        children.add(parent1);
                        children.add(parent2);
                    }
                    for (ListIterator<Function> iter = children.listIterator(); iter.hasNext();) {
                        Function child = iter.next();
                        int mutateChance = ThreadLocalRandom.current().nextInt(100) + 1;
                        if (mutateChance <= 10) {
                            iter.set(mutator.mutate(child));
                        }
                    }
                    currentPopulation.addAll(children);
                });
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
        return randomFunction.nextInt(10000) * randomFunction.nextGaussian();
    }

    private void generateInitialPopulation(int populationSize) {
        for(int i=0; i<populationSize; i++) {
            initialPopulation.add(generateRandomFunction());
        }
    }

}
