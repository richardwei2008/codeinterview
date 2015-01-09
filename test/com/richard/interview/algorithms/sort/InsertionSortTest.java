package com.richard.interview.algorithms.sort;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2015/1/8.
 */
public class InsertionSortTest {

    private InsertionSort obj = new InsertionSort();
    private Logger logger = Logger.getLogger(InsertionSortTest.class.getName());


    @Test
    public void testSort() {
        String[] array = new String[20];
        String[] testData = {
                "a", "b", "c", "d", "e", "f", "g",
                "h", "i", "j", "k", "l", "m", "n",
                "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z"
        };
        for (int i = 0; i < array.length; i++) {
            array[i] = testData[new Random().nextInt(25)];
        }
        logger.log(Level.INFO, Arrays.asList(array).toString());
        obj.sort(array);
        logger.log(Level.INFO, Arrays.asList(array).toString());
    }

    @Test
    public void testSortRange() {
        String[] array = new String[20];
        String[] testData = {
                "a", "b", "c", "d", "e", "f", "g",
                "h", "i", "j", "k", "l", "m", "n",
                "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z"
        };
        String[] index = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            index[i] = String.valueOf(i);
            array[i] = testData[new Random().nextInt(25)];
        }
        logger.log(Level.INFO, Arrays.asList(index).toString());
        logger.log(Level.INFO, Arrays.asList(array).toString());
        int start = 5;
        int end = 15;
        obj.sort(array, start, end);
        logger.log(Level.INFO, Arrays.asList(array).toString());
        Assert.assertTrue(SortTestUtils.isInAsc(array, start, end));
    }



}
