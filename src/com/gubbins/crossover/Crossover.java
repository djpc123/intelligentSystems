package com.gubbins.crossover;

import com.gubbins.Function;

import java.util.ArrayList;

/**
 * Created by Sam on 03/03/2015.
 */
public interface Crossover {

    public default ArrayList<Function> crossover(Function f1, Function f2) {
        ArrayList<Function> children = new ArrayList<>();
        children.add(f1);
        children.add(f2);
        return children;
    }

}
