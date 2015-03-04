package com.gubbins.interfaces;

import com.gubbins.Function;

import java.util.ArrayList;

/**
 * Created by Sam on 03/03/2015.
 */
public interface Tournament {

    public default Function runTournament(ArrayList<Function> population) {
        if(population.size() == 0) return null;
        return population.get(0);
    }

}
