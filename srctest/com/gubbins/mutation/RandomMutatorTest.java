package com.gubbins.mutation;

import com.gubbins.Function;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class RandomMutatorTest {

    @Test
    public void testMutate() throws Exception {
        RandomMutator randomMutator = new RandomMutator();
        Function input = new Function(1, 2, 3, 4, 5, 6);
        Function mutated = randomMutator.mutate(input);
        assertThat(mutated, is(not(input)));
    }
}