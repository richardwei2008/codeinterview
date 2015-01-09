package com.richard.interview.arraysandstrings;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2015/1/8.
 */

// Implement an algorithm to determine if a string has all unique characters. What if you can not use additional data structures?
public class Exercise1a {

    private Logger logger = Logger.getLogger(Exercise1a.class.getName());

    /**
     * If we are allowed to destroy the input string, we could sort the string in O(n log n) time
     * and then linearly check the string for neighboring characters that are identical.
     * Careful, though - many sorting algorithms take up extra space.
     * @param str
     * @return
     */
    public boolean isAllUniqueCharacters(String str) {
        if (null == str) {
            return false;
        }
        if ("" == str) {
            return true;
        }

        return true;
    }

    protected String sort(String str) {
        return null;
    }

    /**
     * linearly check the string for neighboring characters that are identical.
     * @param str not null
     * @return
     */
    protected boolean isContainDuplicateCharacters(String str) {
        if ("" == str) {
            return false;
        }
        for (int i = 0; i < str.length() - 1; i++) {
            char current = str.charAt(i);
            char next = str.charAt(i+1);
            if (current == next) {
                return true;
            }
        }
        return false;
    }
}
