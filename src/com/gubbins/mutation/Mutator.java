package com.gubbins.mutation;

import com.gubbins.Function;

public interface Mutator {

    public default Function mutate(Function f) {
        return f;
    }

}
