package com.richard.interview.algorithms.sort;

/**
 * Created by Administrator on 2015/1/9.
 */
public class InsertionSort {
    public void sort(String[] array) {
        if (null == array) {
            return;
        }
        if (array.length<=1) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            String current = array[i];
            for (int j = 0; j < i; j++) {
                String compare = array[j];
                if (current.compareTo(compare) < 0) {
                    String temp = current;
                    int k = i;
                    for (; k > j; k--) {
                        array[k] = array[k - 1];
                    }
                    array[k] = temp;
                    break;
                }
            }
        }
    }

    /**
     *
     * @param array
     * @param start
     * @param end
     * [start, end)
     */
    public void sort(String[] array, int start, int end) {
        if (null == array) {
            return;
        }
        if (array.length<=1) {
            return;
        }
        if (start > end) {
            throw new IllegalArgumentException("The value of start index can not be larger than the value end index");
        }
        if (end - start <= 1) {
            return;
        }
        for (int i = start; i < end; i++) {
            String current = array[i];
            for (int j = start; j < i; j++) {
                String compare = array[j];
                if (current.compareTo(compare) < 0) {
                    String temp = current;
                    int k = i;
                    for (; k > j; k--) {
                        array[k] = array[k - 1];
                    }
                    array[k] = temp;
                    break;
                }
            }
        }
    }
}
