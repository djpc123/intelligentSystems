package com.intelligentsystems.mutation;

import com.intelligentsystems.Function;

public interface Mutator {

    public default Function mutate(Function f) {
        return f;
    }

}
