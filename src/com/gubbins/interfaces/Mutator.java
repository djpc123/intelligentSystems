package com.gubbins.interfaces;

import com.gubbins.Function;

/**
 * Created by Sam on 03/03/2015.
 */
public interface Mutator {

    public default Function mutate(Function f) {
        return f;
    }

}
