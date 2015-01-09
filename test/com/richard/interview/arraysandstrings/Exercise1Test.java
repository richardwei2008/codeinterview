package com.richard.interview.arraysandstrings;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2015/1/8.
 */
public class Exercise1Test {

    private Exercise1 obj = new Exercise1();

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
