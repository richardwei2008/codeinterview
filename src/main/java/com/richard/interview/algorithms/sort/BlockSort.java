package com.richard.interview.algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2015/1/8.
 */
public class BlockSort {

    private Logger logger = Logger.getLogger(BlockSort.class.getName());

    protected int floorPowerOfTwo(int x) {
        x = x | (x>>1);
        x = x | (x>>2);
        x = x | (x>>4);
        x = x | (x>>8);
        x = x | (x>>16);
        return x - (x >> 1);
    }

    public void sort(String[] array) {
        if (null == array) {
            return;
        }
        if (array.length <= 1) {
            return;
        }
        int powerOfTwo = this.floorPowerOfTwo(array.length);
        logger.log(Level.INFO, String.format("Power of Two: %d", powerOfTwo));
        int scale = array.length / powerOfTwo;
        logger.log(Level.INFO, String.format("Scale: %d", scale));
        InsertionSort insertionSort = new InsertionSort();
        for (int merge = 0; merge < powerOfTwo; merge=merge+16) {
            int start = merge * scale;
            int end = (merge + 16) * scale;
            logger.log(Level.INFO, String.format("InsertionSort(array, [%d, %d))", start, end));
            insertionSort.sort(array, start, end);
        }

        for (int length = 16; length < powerOfTwo; length = length * 2) {
            logger.log(Level.INFO, String.format("Length: %d", length));
            for (int merge = 0; merge < powerOfTwo; merge = merge + length * 2) {
                logger.log(Level.INFO, String.format("Merge: %d", merge));
                int start = merge * scale;
                int mid = (merge + length) * scale;
                int end = (merge + length * 2) * scale;
                logger.log(Level.INFO, String.format("[start, end)=[%d, %d))", start, end));
                if (array[end - 1].compareTo(array[start]) < 0) {
                    logger.log(Level.INFO, String.format("Rotate(array, mid-start=%d, [start, end)=[%d, %d))", mid - start, start, end));
                    int amount = mid-start;
                    rotate(array, amount, start, end);
                } else if (array[mid - 1].compareTo(array[mid]) > 0) {
                    logger.log(Level.INFO, String.format("Merge(array, A=[%d, %d), B=[%d, %d))", start, mid, mid, end));
                    merge(array, start, mid, mid, end);
                }
            }
        }
    }


    protected void rotate(String[] array, int amount, int start, int end) {
        logger.log(Level.INFO, String.format("Reverse Range [start, end)=[%d, %d)", start, end));
        reverse(array, start, end);
        logger.log(Level.INFO, Arrays.asList(array).toString());
        logger.log(Level.INFO, String.format("Reverse Range [start, end)=[%d, %d)", start, start + amount));
        reverse(array, start, start + amount);
        logger.log(Level.INFO, Arrays.asList(array).toString());
        logger.log(Level.INFO, String.format("Reverse Range [start, end)=[%d, %d)",  start + amount, end));
        reverse(array, start + amount, end);
        logger.log(Level.INFO, Arrays.asList(array).toString());
    }

    protected void reverse(String[] array, int start, int end) {
        if (array == null) {
            throw new IllegalArgumentException("Array can not be null");
        }
        if (start > end) {
            throw new IllegalArgumentException("The value of start index can not be larger than the value end index");
        }
        if (end - start <= 1) {
            return;
        }
//        int length = end - start;
        for (int i = start; i < (start + end) / 2; i++) {
            int pairFrom = i;
            int pairTo = start + end - pairFrom - 1;
            logger.log(Level.INFO, String.format("[from, to]=[%d, %d]", pairFrom,pairTo));
            String temp = array[pairTo];
            array[pairTo] = array[pairFrom];
            array[pairFrom] = temp;
        }
    }

    protected void merge(String[] array, int start, int midA, int midB, int end) {
        // extract buffers
        boolean isFirst = true;
        extractBuffers(array, start, midA, isFirst);
        extractBuffers(array, midB, end, !isFirst);
    }

    protected void extractBuffers(String[] array, int start, int end, boolean isFirst) {
        if (array == null) {
            throw new IllegalArgumentException("Array can not be null");
        }
        if (start > end) {
            throw new IllegalArgumentException("The value of start index can not be larger than the value end index");
        }
        if (end - start <= 1) {
            return;
        }
        int bufferSize = Double.valueOf(Math.sqrt(end - start)).intValue();
        int[] positions = new int[bufferSize];
        int count = 0;
        if (isFirst) {
            String tag = array[start];
            positions[count++] = start;
            for (int i = start; i < end; i++) {
                String now = array[i];
                if (tag.compareTo(now) < 0) {
                    tag = now;
                    positions[count++] = i;
                }
            }
            for (int i = positions.length - 1; i > 0 ; i--) {
                int from = positions[i - 1];
                int to = positions[i];
                String temp = array[to];
                for (int j = to; j > from; j--) {
                    array[j] = array[j-1];
                }
                array[from + 1] = temp;
            }
        }

    }
}
