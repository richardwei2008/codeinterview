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
public class BlockSortTest {

    private BlockSort obj = new BlockSort();
    private Logger logger = Logger.getLogger(BlockSortTest.class.getName());

    @Test
    public void testFloorPowerOfTwo_63() {
        int powerOfTwo = obj.floorPowerOfTwo(63);
        logger.log(Level.INFO, "Power of Two: " + String.valueOf(powerOfTwo));
        Assert.assertEquals(32, powerOfTwo);
    }

    @Test
    public void testFloorPowerOfTwo_64() {
        int powerOfTwo = obj.floorPowerOfTwo(64);
        logger.log(Level.INFO, "Power of Two: " + String.valueOf(powerOfTwo));
        Assert.assertEquals(64, powerOfTwo);
    }

    @Test
    public void testFloorPowerOfTwo_97() {
        int powerOfTwo = obj.floorPowerOfTwo(137);
        logger.log(Level.INFO, "Power of Two: " + String.valueOf(powerOfTwo));
        Assert.assertEquals(128, powerOfTwo);
    }

    @Test
    public void testSort() {
        String[] array = new String[200];
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
    public void testReverse_even() {
        String[] array = new String[8];
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
        obj.reverse(array, 0, array.length);
        logger.log(Level.INFO, Arrays.asList(array).toString());
    }

    @Test
    public void testReverse_odd() {
        String[] array = new String[9];
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
        obj.reverse(array, 0, array.length);
        logger.log(Level.INFO, Arrays.asList(array).toString());
    }

    @Test
    public void testRotate() {
        String[] array = {
                "i", "j", "k", "l", "m", "n", "o", "p",
                "a", "b", "c", "d", "e", "f", "g", "h"
        };
        logger.log(Level.INFO, Arrays.asList(array).toString());
        obj.rotate(array, 8, 0, array.length);
        logger.log(Level.INFO, Arrays.asList(array).toString());
    }
}
