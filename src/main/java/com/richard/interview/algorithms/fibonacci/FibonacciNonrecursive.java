package com.richard.interview.algorithms.fibonacci;

/**
 * Created by Richard on 17/3/16.
 *
 * fibonacci 0 1 1 2 3 5 8 13
 */
public class FibonacciNonrecursive {

    public static int MAX = 100000;


    /**
     *
     * @param n the position nth number in the fibonacci series (n start from 0)
     * @return
     */
    public int fibonacci(int n) throws StackOverflowError {
        if (n > 46) {
            throw new IllegalArgumentException("The maximum i is limited to " + MAX);
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int prev1 = 0;
        int prev2 = 1;
        int result = prev1 + prev2;
        for (int i = 2; i <= n; i++) {
            result = prev1 + prev2;
            prev1 = prev2;
            prev2 = result;
        }
        return result;
    }
}
