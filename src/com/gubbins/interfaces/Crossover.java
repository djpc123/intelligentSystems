package com.gubbins.interfaces;

import com.gubbins.Function;

/**
 * Created by Sam on 03/03/2015.
 */
public interface Crossover {

    public default Function crossover(Function f1, Function f2) {
        return f1;
    }

}
