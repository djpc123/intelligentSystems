package com.gubbins.tornament;

import com.gubbins.Function;
import com.gubbins.elitism.Elitism;
import com.gubbins.fitness.Fitness;

import java.util.ArrayList;
import java.util.Random;

public class Tournament {

    private Random randomSelect;
    private Elitism elitism;

    public Tournament() {
        randomSelect = new Random();
        elitism = new Elitism();
    }

    public Function runTournament(ArrayList<Function> population) {
        ArrayList<Function> tournament = new ArrayList<>();
        for(int i=0; i<5; i++) {
            tournament.add(population.get(randomSelect.nextInt(population.size())));
        }
        Function winner = elitism.selectElite(1, tournament).get(0);
        return winner;
    }

}
