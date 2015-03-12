package com.gubbins;

import com.gubbins.crossover.Crossover;
import com.gubbins.crossover.SingleSplitCrossover;
import com.gubbins.elitism.Elitism;
import com.gubbins.mutation.Mutator;
import com.gubbins.mutation.RandomMutator;
import com.gubbins.tornament.Tournament;

import java.util.*;
import java.util.concurrent.*;

public class GeneticAlgorithm {

    private final ExecutorService executor = Executors.newFixedThreadPool(40);
    private Random randomFunction = new Random();
    private Mutator mutator = new RandomMutator();
    private Crossover crossover = new SingleSplitCrossover();
    private Tournament tournament = new Tournament();
    private ArrayList<Function> initialPopulation = new ArrayList<>();
    private GubChart chart;
    private int populationSize = 50000;
    private int eliteNumber = (populationSize/100)*1;
    private int randomNumber = (populationSize/100)*1;


    public GeneticAlgorithm(GubChart gub) {
        chart = gub;
    }

    public void run() {
        generateInitialPopulation(populationSize);
        ArrayList<Double> bestFitness = new ArrayList<>();
        ArrayList<Double> worstFitness = new ArrayList<>();
        ArrayList<Double> averageFitness = new ArrayList<>();
        ArrayList<Integer> time = new ArrayList<>();

        for(int j=0; j<1000; j++) {
            List<Function> currentPopulation = Collections.synchronizedList(new ArrayList<>());
            List<Future<?>> tasks = new ArrayList<>();
            currentPopulation.addAll(Elitism.selectElite(eliteNumber, initialPopulation));

            for (int i = currentPopulation.size(); i < (populationSize - randomNumber); i+=2) {
                tasks.add( executor.submit(() -> {
                    Function parent1 = tournament.runTournament(initialPopulation);
                    Function parent2 = tournament.runTournament(initialPopulation);
                    int crossoverChance = ThreadLocalRandom.current().nextInt(100) + 1;
                    ArrayList<Function> children = new ArrayList<>();
                    if (crossoverChance <= 75) {
                        children.addAll(crossover.crossover(parent1, parent2));
                    } else {
                        children.add(parent1);
                        children.add(parent2);
                    }
                    for (ListIterator<Function> iter = children.listIterator(); iter.hasNext(); ) {
                        Function child = iter.next();
                        int mutateChance = ThreadLocalRandom.current().nextInt(100) + 1;
                        if (mutateChance <= 15) {
                            iter.set(mutator.mutate(child));
                        }
                    }
                    currentPopulation.addAll(children);
                }));
            }

            for (Future<?> task : tasks) {
                try {
                    task.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            for (int i = currentPopulation.size(); i < populationSize; i++) {
                currentPopulation.add(generateRandomFunction());
            }

            initialPopulation.clear();
            initialPopulation.addAll(currentPopulation);
            currentPopulation.clear();

            Function best = Elitism.selectElite(1, initialPopulation).get(0);
            System.out.println(j + " " + best.getFitness());
//            chart.addSeries(best.toString(), "best",DataReader.getX(), best.calculate(DataReader.getX()));

            bestFitness.add(best.getFitness());
            Function worst = Elitism.selectElite(populationSize, initialPopulation).get(populationSize-1);
            worstFitness.add(worst.getFitness());
            double averageFit = 0;
            Iterator<Function> it = initialPopulation.iterator();
            while(it.hasNext()) {
                averageFit += it.next().getFitness();
            }
            averageFit /= populationSize;
            averageFitness.add(averageFit);
            time.add(j);
            if(best.getFitness() == 0) break;
        }

        GubChart c = new GubChart("Fitness", "Time", "Fitness Value");
        c.setLog();
        c.addSeries("Average", time, averageFitness);
        c.addSeries("Worst", time, worstFitness);
        c.addSeries("Best", time, bestFitness);
        c.save("fitness");

        Function best = Elitism.selectElite(1, initialPopulation).get(0);
        GubChart bestC = new GubChart("Best", "x", "f(x)");
        bestC.addSeries("f(x)", DataReader.getX(), DataReader.getY());
        bestC.addSeries("Best", DataReader.getX(), best.calculate(DataReader.getX()));
        bestC.save(best.toString());

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
        return randomFunction.nextInt(1000) * randomFunction.nextGaussian();
    }

    private void generateInitialPopulation(int populationSize) {
        for(int i=0; i<populationSize; i++) {
            initialPopulation.add(generateRandomFunction());
        }
    }

}
