package com.intelligentsystems;

import com.intelligentsystems.crossover.Crossover;
import com.intelligentsystems.crossover.SingleSplitCrossover;
import com.intelligentsystems.elitism.Elitism;
import com.intelligentsystems.mutation.Mutator;
import com.intelligentsystems.mutation.RandomMutator;
import com.intelligentsystems.tornament.Tournament;

import java.util.*;
import java.util.concurrent.*;

public class GeneticAlgorithm {

    private final ExecutorService executor = Executors.newFixedThreadPool(40);
    private Random randomFunction = new Random();
    private Mutator mutator = new RandomMutator();
    private Crossover crossover = new SingleSplitCrossover();
    private Tournament tournament = new Tournament();
    private ArrayList<Function> initialPopulation = new ArrayList<>();
    private int populationSize = 10000;
    private int eliteNumber = (populationSize/100)*5;
    private int randomNumber = (populationSize/100)*1;


    public GeneticAlgorithm() {
        System.out.println(new Function(5.297181794440944E8, -2.609078261378958E7, 28588.4832148207, 544.0508453616947, -0.559004764694197, 0).getFitness());
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
                    if (crossoverChance <= 70) {
                        children.addAll(crossover.crossover(parent1, parent2));
                    } else {
                        children.add(parent1);
                        children.add(parent2);
                    }
                    for (ListIterator<Function> iter = children.listIterator(); iter.hasNext(); ) {
                        Function child = iter.next();
                        int mutateChance = ThreadLocalRandom.current().nextInt(100) + 1;
                        if (mutateChance <= 10) {
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

        Chart c = new Chart("Fitness", "Time", "Fitness Value");
        c.setLog();
        c.addSeries("Average", time, averageFitness);
        c.addSeries("Worst", time, worstFitness);
        c.addSeries("Best", time, bestFitness);
        c.save("fitness");

        Function best = Elitism.selectElite(1, initialPopulation).get(0);
        Chart bestC = new Chart("Best", "x", "f(x)");
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
