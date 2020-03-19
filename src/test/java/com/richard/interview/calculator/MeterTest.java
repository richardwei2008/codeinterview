package com.richard.interview.calculator;

import junit.framework.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MeterTest {

    @Test
    public void testCalculateThePrice() {
        LocalDateTime pickupTime = null;
        LocalDateTime deliveryTime = null;
        boolean longHaulRate = false;
        List<Double> distances = new ArrayList<>();

        Meter meter = new Meter();
        meter.setSequentialDistances(distances);

        BigDecimal price = meter.calculateThePrice(pickupTime, deliveryTime, longHaulRate, distances);

        Assert.assertEquals(BigDecimal.ZERO, price);
    }

    @Test
    public void testCalculateThePrice_day_init() {
        LocalDateTime pickupTime = LocalDateTime.of(2020, 3, 19, 9, 23, 00);
        LocalDateTime deliveryTime = LocalDateTime.of(2020, 3, 19, 9, 54, 00);
        boolean longHaulRate = false;
        List<Double> distances = new ArrayList<>();
        distances.add(2.3);

        Meter meter = new Meter();
        meter.setSequentialDistances(distances);

        BigDecimal price = meter.calculateThePrice(pickupTime, deliveryTime, longHaulRate, distances);
        // 2.3 < 3 => 14
        System.out.println("Price: " + price);
        Assert.assertEquals(0, BigDecimal.valueOf(14.0).compareTo(price));
    }

    @Test
    public void testCalculateThePrice_night_init() {
        LocalDateTime pickupTime = LocalDateTime.of(2020, 3, 19, 23, 59, 00);
        LocalDateTime deliveryTime = LocalDateTime.of(2020, 3, 20, 00, 10, 00);
        boolean longHaulRate = false;
        List<Double> distances = new ArrayList<>();
        distances.add(2.9);

        Meter meter = new Meter();
        meter.setSequentialDistances(distances);

        BigDecimal price = meter.calculateThePrice(pickupTime, deliveryTime, longHaulRate, distances);
        // 2.9 < 3 => 18
        System.out.println("Price: " + price);
        Assert.assertEquals(0, BigDecimal.valueOf(18.0).compareTo(price));
    }

    @Test
    public void testCalculateThePrice_day_haulage() {
        LocalDateTime pickupTime = LocalDateTime.of(2020, 3, 19, 9, 23, 00);
        LocalDateTime deliveryTime = LocalDateTime.of(2020, 3, 19, 10, 54, 00);
        boolean longHaulRate = false;
        List<Double> distances = new ArrayList<>();
        distances.add(9.8);

        Meter meter = new Meter();
        meter.setSequentialDistances(distances);

        BigDecimal price = meter.calculateThePrice(pickupTime, deliveryTime, longHaulRate, distances);
        // 9.8 > 3 & 9.8 < 10 => 14 + (9.8 - 3) * 2.5
        System.out.println("Price: " + price);
        Assert.assertEquals(0, BigDecimal.valueOf(31.0).compareTo(price));
    }

    @Test
    public void testCalculateThePrice_day_long_haulage() {
        LocalDateTime pickupTime = LocalDateTime.of(2020, 3, 19, 9, 23, 00);
        LocalDateTime deliveryTime = LocalDateTime.of(2020, 3, 19, 11, 54, 00);
        boolean longHaulRate = true;
        List<Double> distances = new ArrayList<>();
        distances.add(14.8);

        Meter meter = new Meter();
        meter.setSequentialDistances(distances);

        BigDecimal price = meter.calculateThePrice(pickupTime, deliveryTime, longHaulRate, distances);
        // 14.8 > 10 => 14 + 7 * 2.5 + 4.8 * 3.5
        System.out.println("Price: " + price);
        Assert.assertEquals(0, BigDecimal.valueOf(48.3).compareTo(price));
    }

    @Test
    public void testCalculateThePrice_day_long_haulage_suburban() {
        LocalDateTime pickupTime = LocalDateTime.of(2020, 3, 19, 9, 23, 00);
        LocalDateTime deliveryTime = LocalDateTime.of(2020, 3, 19, 11, 54, 00);
        boolean longHaulRate = false;
        List<Double> distances = new ArrayList<>();
        distances.add(14.8);

        Meter meter = new Meter();
        meter.setSequentialDistances(distances);

        BigDecimal price = meter.calculateThePrice(pickupTime, deliveryTime, longHaulRate, distances);
        // 14.8 > 10 => 14 + 7 * 2.5 + 4.8 * 2.5
        System.out.println("Price: " + price);
        Assert.assertEquals(0, BigDecimal.valueOf(43.5).compareTo(price));
    }


    @Test
    public void testCalculateThePrice_night_haulage() {
        LocalDateTime pickupTime = LocalDateTime.of(2020, 3, 19, 23, 23, 00);
        LocalDateTime deliveryTime = LocalDateTime.of(2020, 3, 19, 23, 54, 00);
        boolean longHaulRate = false;
        List<Double> distances = new ArrayList<>();
        distances.add(9.8);

        Meter meter = new Meter();
        meter.setSequentialDistances(distances);

        BigDecimal price = meter.calculateThePrice(pickupTime, deliveryTime, longHaulRate, distances);
        // 9.8 > 3 & 9.8 < 10 => 18 + (9.8 - 3) * 3.0
        System.out.println("Price: " + price);
        Assert.assertEquals(0, BigDecimal.valueOf(38.4).compareTo(price));
    }

    @Test
    public void testCalculateThePrice_night_long_haulage() {
        LocalDateTime pickupTime = LocalDateTime.of(2020, 3, 19, 23, 23, 00);
        LocalDateTime deliveryTime = LocalDateTime.of(2020, 3, 19, 23, 54, 00);
        boolean longHaulRate = true;
        List<Double> distances = new ArrayList<>();
        distances.add(14.8);

        Meter meter = new Meter();
        meter.setSequentialDistances(distances);

        BigDecimal price = meter.calculateThePrice(pickupTime, deliveryTime, longHaulRate, distances);
        // 14.8 > 10 => 18 + 7 * 3 + 4.8 * 4.7
        System.out.println("Price: " + price);
        Assert.assertEquals(0, BigDecimal.valueOf(61.56).compareTo(price));
    }

    @Test
    public void testCalculateThePrice_night_long_haulage_suburban() {
        LocalDateTime pickupTime = LocalDateTime.of(2020, 3, 19, 23, 23, 00);
        LocalDateTime deliveryTime = LocalDateTime.of(2020, 3, 20, 0, 14, 00);
        boolean longHaulRate = false;
        List<Double> distances = new ArrayList<>();
        distances.add(14.8);

        Meter meter = new Meter();
        meter.setSequentialDistances(distances);

        BigDecimal price = meter.calculateThePrice(pickupTime, deliveryTime, longHaulRate, distances);
        // 14.8 > 10 => 18 + 7 * 3 + 4.8 * 3
        System.out.println("Price: " + price);
        Assert.assertEquals(0, BigDecimal.valueOf(53.4).compareTo(price));
    }



    @Test
    public void testCalculateThePrice_day_night_init() {
        LocalDateTime pickupTime = LocalDateTime.of(2020, 3, 19, 22, 50, 00);
        LocalDateTime deliveryTime = LocalDateTime.of(2020, 3, 19, 23, 14, 00);
        boolean longHaulRate = false;
        List<Double> distances = new ArrayList<>();
        distances.add(1.2);
        distances.add(1.5);

        Meter meter = new Meter();
        meter.setSequentialDistances(distances);

        BigDecimal price = meter.calculateThePrice(pickupTime, deliveryTime, longHaulRate, distances);
        // 2.7 < 3 => 14
        System.out.println("Price: " + price);
        Assert.assertEquals(0, BigDecimal.valueOf(14.0).compareTo(price));
    }

    @Test
    public void testCalculateThePrice_night_day_init() {
        LocalDateTime pickupTime = LocalDateTime.of(2020, 3, 20, 5, 50, 00);
        LocalDateTime deliveryTime = LocalDateTime.of(2020, 3, 20, 6, 14, 00);
        boolean longHaulRate = false;
        List<Double> distances = new ArrayList<>();
        distances.add(1.1);
        distances.add(1.3);

        Meter meter = new Meter();
        meter.setSequentialDistances(distances);

        BigDecimal price = meter.calculateThePrice(pickupTime, deliveryTime, longHaulRate, distances);
        // 2.7 < 3 => 18
        System.out.println("Price: " + price);
        Assert.assertEquals(0, BigDecimal.valueOf(18.0).compareTo(price));
    }

    @Test
    public void testCalculateThePrice_day_night() {
        LocalDateTime pickupTime = LocalDateTime.of(2020, 3, 19, 22, 50, 00);
        LocalDateTime deliveryTime = LocalDateTime.of(2020, 3, 20, 0, 14, 00);
        boolean longHaulRate = true;
        List<Double> distances = new ArrayList<>();
        distances.add(1.2);
        distances.add(5.9);

        Meter meter = new Meter();
        meter.setSequentialDistances(distances);

        BigDecimal price = meter.calculateThePrice(pickupTime, deliveryTime, longHaulRate, distances);
        // 7.1 > 10 => 14(3) + 3*(4.1)
        System.out.println("Price: " + price);
        Assert.assertEquals(0, BigDecimal.valueOf(26.3).compareTo(price));
    }

    @Test
    public void testCalculateThePrice_day_night_long_haul() {
        LocalDateTime pickupTime = LocalDateTime.of(2020, 3, 19, 22, 50, 00);
        LocalDateTime deliveryTime = LocalDateTime.of(2020, 3, 20, 0, 14, 00);
        boolean longHaulRate = true;
        List<Double> distances = new ArrayList<>();
        distances.add(1.2);
        distances.add(15.9);

        Meter meter = new Meter();
        meter.setSequentialDistances(distances);

        BigDecimal price = meter.calculateThePrice(pickupTime, deliveryTime, longHaulRate, distances);
        // 17.1 > 10 => 14(3) + 3*(7) + 4.7*(7.1)
        System.out.println("Price: " + price);
        Assert.assertEquals(0, BigDecimal.valueOf(68.37).compareTo(price));
    }

    @Test
    public void testCalculateThePrice_day_night_long_haul_() {
        LocalDateTime pickupTime = LocalDateTime.of(2020, 3, 19, 21, 50, 00);
        LocalDateTime deliveryTime = LocalDateTime.of(2020, 3, 19, 23, 4, 00);
        boolean longHaulRate = true;
        List<Double> distances = new ArrayList<>();
        distances.add(15.9);
        distances.add(1.2);

        Meter meter = new Meter();
        meter.setSequentialDistances(distances);

        BigDecimal price = meter.calculateThePrice(pickupTime, deliveryTime, longHaulRate, distances);
        // 17.1 > 10 => 14(3) + 2.5*(7) + 3.5*(5.9) + 4.7*(1.2)
        System.out.println("Price: " + price);
        Assert.assertEquals(0, BigDecimal.valueOf(57.79).compareTo(price));
    }

    @Test
    public void testCalculateThePrice_day_night_long_haul_suburban() {
        LocalDateTime pickupTime = LocalDateTime.of(2020, 3, 19, 22, 50, 00);
        LocalDateTime deliveryTime = LocalDateTime.of(2020, 3, 20, 0, 14, 00);
        boolean longHaulRate = false;
        List<Double> distances = new ArrayList<>();
        distances.add(1.2);
        distances.add(15.9);

        Meter meter = new Meter();
        meter.setSequentialDistances(distances);

        BigDecimal price = meter.calculateThePrice(pickupTime, deliveryTime, longHaulRate, distances);
        // 17.1 > 10 => 14(3) + 3*(7) + 3*(7.1)
        System.out.println("Price: " + price);
        Assert.assertEquals(0, BigDecimal.valueOf(56.30).compareTo(price));
    }

}
