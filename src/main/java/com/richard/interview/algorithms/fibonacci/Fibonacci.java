package com.richard.interview.algorithms.fibonacci;

/**
 * Created by Richard on 17/3/16.
 *
 * fibonacci 0 1 1 2 3 5 8 13
 */
public class Fibonacci {

    public static int MAX = 100000;

    /**
     *
     * @param n the position nth number in the fibonacci series (n start from 0)
     * @return
     */
    public int fibonacci(int n) {
        if (n > 46) {
            throw new IllegalArgumentException("The maximum i is limited to " + MAX);
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

}
