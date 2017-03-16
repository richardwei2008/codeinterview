package com.richard.interview.algorithms.sort;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2015/1/9.
 */
public class SortTestUtils {
    private static Logger logger = Logger.getLogger(SortTestUtils.class.getName());
    /**
     * [start, end)
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static boolean isInAsc(String[] array, int start, int end) {
        if (array == null) {
            throw new IllegalArgumentException("Array can not be null");
        }
        if (start > end) {
            throw new IllegalArgumentException("The value of start index can not be larger than the value end index");
        }
        if (end - start <= 1) {
            return true;
        }
        boolean isInAsc = true;
        for (int i = start + 1; i < end; i++) {
            logger.log(Level.INFO, String.format("Current index: %d", i));
            if (array[i].compareTo(array[i-1]) < 0) {
                isInAsc = false;
            }
        }
        return isInAsc;
    }
}
