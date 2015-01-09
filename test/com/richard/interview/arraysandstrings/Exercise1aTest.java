package com.richard.interview.arraysandstrings;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2015/1/8.
 */
public class Exercise1aTest {

    private Exercise1a obj = new Exercise1a();

    /**
     * Test for sort
     */


    /**
     * Test for linear check duplication
     */

    @Test
    public void testIsContainDuplicateCharacters_emptyString() {
        Assert.assertFalse(obj.isContainDuplicateCharacters(""));
    }

    @Test
    public void testIsContainDuplicateCharacters_abcd() {
        Assert.assertFalse(obj.isContainDuplicateCharacters("abcd"));
    }

    @Test
    public void testIsContainDuplicateCharacters_abbc() {
        Assert.assertTrue(obj.isContainDuplicateCharacters("abbc"));
    }

    /**
     * Test for main logic
     */

    @Test
    public void testIsAllUniqueCharacters_null() {
        Assert.assertFalse(obj.isAllUniqueCharacters(null));
    }

    @Test
    public void testIsAllUniqueCharacters_emptyString() {
        Assert.assertTrue(obj.isAllUniqueCharacters(""));
    }

    @Test
    public void testIsAllUniqueCharacters_abc() {
        Assert.assertTrue(obj.isAllUniqueCharacters("abc"));
    }

    @Test
    public void testIsAllUniqueCharacters_abb() {
        Assert.assertFalse(obj.isAllUniqueCharacters("abb"));
    }
}
