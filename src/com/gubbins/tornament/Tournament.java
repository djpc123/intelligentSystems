package com.gubbins.tornament;

import com.gubbins.Function;
import com.gubbins.elitism.Elitism;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Tournament {

    private Random randomSelect = new Random();
    private static int TOURNAMENT_SIZE = 500;

    public Function runTournament(ArrayList<Function> population) {
        ArrayList<Function> tournament = new ArrayList<>();
        ArrayList<Integer> indexs = new ArrayList<>();
        while(indexs.size() < TOURNAMENT_SIZE) {
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
