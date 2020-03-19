package com.richard.interview.calculator;


import java.time.LocalDateTime;
import java.time.LocalTime;

public class RateCard {

    private static double DAY_INIT = 14;

    private static double DAY_CHARGE_PER_KILOMETER = 2.5;

    private static double DAY_LONG_DISTANCE_CHARGE_PER_KILOMETER = 3.5;

    private static double NIGHT_INIT = 18;

    private static double NIGHT_CHARGE_PER_KILOMETER = 3;

    private static double NIGHT_LONG_DISTANCE_CHARGE_PER_KILOMETER = 4.7;

    private static LocalTime DAY_FROM = LocalTime.of(6, 0);

    private static LocalTime DAY_TO = LocalTime.of(23, 0);

    private double initCharge;

    private double unitCharge;

    private double longHaulageCharge;

    private RateCard() {
        this.initCharge = DAY_INIT;
        this.unitCharge = DAY_CHARGE_PER_KILOMETER;
        this.longHaulageCharge = DAY_LONG_DISTANCE_CHARGE_PER_KILOMETER;
    }

    private RateCard(double initCharge, double unitCharge, double longHaulageCharge) {
        this.initCharge = initCharge;
        this.unitCharge = unitCharge;
        this.longHaulageCharge = longHaulageCharge;
    }

    public static RateCard get(LocalDateTime currentTime) {
        if (dayHours(currentTime)) {
            // return default day service rate
            return new RateCard();
        } else {
            return new RateCard(NIGHT_INIT, NIGHT_CHARGE_PER_KILOMETER, NIGHT_LONG_DISTANCE_CHARGE_PER_KILOMETER);
        }
    }

    public static RateCard getDayRate() {
        return new RateCard();
    }

    public static RateCard getNightRate() {
        return new RateCard(NIGHT_INIT, NIGHT_CHARGE_PER_KILOMETER, NIGHT_LONG_DISTANCE_CHARGE_PER_KILOMETER);
    }

    public static boolean dayHours(LocalDateTime localDateTime) {
        LocalDateTime from = LocalDateTime.of(localDateTime.toLocalDate(), DAY_FROM);
        LocalDateTime to = LocalDateTime.of(localDateTime.toLocalDate(), DAY_TO);

        return (localDateTime.isEqual(from) ||
                (localDateTime.isAfter(from) && localDateTime.isBefore(to)));
    }


    public double getInitCharge() {
        return initCharge;
    }

    public double getUnitCharge() {
        return unitCharge;
    }

    public double getLongHaulageCharge() {
        return longHaulageCharge;
    }
}
