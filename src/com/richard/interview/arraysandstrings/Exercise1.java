package com.richard.interview.arraysandstrings;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2015/1/8.
 */

// Implement an algorithm to determine if a string has all unique characters. What if you can not use additional data structures?
public class Exercise1 {

    private Logger logger = Logger.getLogger(Exercise1.class.getName());

    /**
     * Check every char of the string with every other char of the string for duplicate occurrences. This will take O(n^2) time and no space.
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
        for(int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            for (int j = i+1; j < str.length(); j++) {
                char compare = str.charAt(j);
                logger.log(Level.INFO, "Current i: " + String.valueOf(i));
                logger.log(Level.INFO, "Current: " + String.valueOf(current));
                logger.log(Level.INFO, "Current j: " + String.valueOf(j));
                logger.log(Level.INFO, "Compare: " + String.valueOf(compare));
                if (current == compare) {
                    return false;
                }
            }
        }
        return true;
    }
}
