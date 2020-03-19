package com.richard.interview.calculator;

import junit.framework.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class RateCardTest {

    @Test
    public void testNightHours_at_eleven() {
        LocalDateTime time = LocalDateTime.of(2020, 3, 18, 23, 0,  0);
        boolean inNightHours = !RateCard.dayHours(time);
        Assert.assertEquals(true, inNightHours);
    }

    @Test
    public void testNightHours_before_eleven() {
        LocalDateTime time = LocalDateTime.of(2020, 3, 18, 22, 59,  59);
        boolean inNightHours = !RateCard.dayHours(time);
        Assert.assertEquals(false, inNightHours);
    }

    @Test
    public void testNightHours_in_between() {
        LocalDateTime time = LocalDateTime.of(2020, 3, 18, 23, 59,  59);
        boolean inNightHours = !RateCard.dayHours(time);
        Assert.assertEquals(true, inNightHours);
    }

    @Test
    public void testNightHours_at_six() {
        LocalDateTime time = LocalDateTime.of(2020, 3, 19, 6, 0,  0);
        boolean inNightHours = !RateCard.dayHours(time);
        Assert.assertEquals(false, inNightHours);
    }

    @Test
    public void testNightHours_after_six() {
        LocalDateTime time = LocalDateTime.of(2020, 3, 19, 7, 0,  0);
        boolean inNightHours = !RateCard.dayHours(time);
        Assert.assertEquals(false, inNightHours);
    }
}
