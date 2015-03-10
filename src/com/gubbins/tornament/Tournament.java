package com.gubbins.tornament;

import com.gubbins.Function;
import com.gubbins.elitism.Elitism;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Tournament {

    private Random randomSelect = new Random();
    private int tournamentSize = 5;

    public Function runTournament(ArrayList<Function> population) {
        ArrayList<Function> tournament = new ArrayList<>();
        ArrayList<Integer> indexs = new ArrayList<>();
        while(indexs.size() < tournamentSize) {
            int index = randomSelect.nextInt(population.size());
            if(!indexs.contains(index)) {
                indexs.add(index);
            }
        }
        Iterator<Integer> it = indexs.iterator();
        while(it.hasNext()) {
            tournament.add(population.get(it.next()));
        }
        return Elitism.selectElite(1, tournament).get(0);
    }

}
