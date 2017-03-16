package com.richard.interview.algorithms.fibonacci;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Richard on 17/3/16.
 *
 * fibonacci 0 1 1 2 3 5 8 13
 */
public class FibonacciNonrecursiveTest {

    private FibonacciNonrecursive fib = new FibonacciNonrecursive();

    @Test
    public void testFibonacci_0() {
        Assert.assertEquals(0, fib.fibonacci(0));
    }

    @Test
    public void testFibonacci_1() {
        Assert.assertEquals(1, fib.fibonacci(1));
    }

    @Test
    public void testFibonacci_theSixth() {
        Assert.assertEquals(13, fib.fibonacci(7));
    }


}
