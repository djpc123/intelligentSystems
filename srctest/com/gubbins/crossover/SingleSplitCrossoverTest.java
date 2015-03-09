package com.gubbins.crossover;

import com.gubbins.Function;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class SingleSplitCrossoverTest {

    @Test
    public void testCrossover() throws Exception {
        SingleSplitCrossover singleSplitCrossover = new SingleSplitCrossover();
        Function input1 = new Function(1, 2, 3, 4, 5, 6);
        Function input2 = new Function(7,8,9,10,11,12);
        ArrayList<Function> children = singleSplitCrossover.crossover(input1, input2);
        assertThat(children.get(1), is(not(input1)));
    }
}