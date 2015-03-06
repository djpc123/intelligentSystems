package com.gubbins.tornament;

import com.gubbins.Function;

import java.util.ArrayList;

public interface Tournament {

    public default Function runTournament(ArrayList<Function> population) {
        if(population.size() == 0) return null;
        return population.get(0);
    }

}
