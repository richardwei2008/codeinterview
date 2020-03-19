package com.richard.interview.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

public class Meter {

    private static double INIT_DISTANCE = 3.0;

    private static double LONG_DISTANCE = 10.0;

    private LocalDateTime pickupTime;

    private LocalDateTime deliveryTime;

    /**
     * e.g. scenario 1
     * pickup time - 22:00
     * night time - 23:00
     * delivery time - 23:30
     *
     * 15:00 ~ 23:00 sequentialDistances[0] = 12KM
     * 23:00 ~ 23:30 sequentialDistances[1] = 9KM
     */
    private List<Double> sequentialDistances;

    private boolean longHaulage(double currentDistance) {
        return currentDistance > LONG_DISTANCE;
    }

    private boolean initHaulage(double currentDistance) {
        return currentDistance <= INIT_DISTANCE;
    }

    public BigDecimal currentPrice(boolean longHaulRate) {
        return calculateThePrice(this.pickupTime, this.deliveryTime, longHaulRate, this.sequentialDistances);
    }


    protected BigDecimal calculateThePrice(LocalDateTime pickupTime, LocalDateTime deliveryTime, boolean longHaulRate, List<Double> sequentialDistances) {
        if (pickupTime == null) {
            return BigDecimal.ZERO;
        }
        if (sequentialDistances == null || sequentialDistances.isEmpty()) {
            return BigDecimal.ZERO;
        }
        if (deliveryTime == null) {
            deliveryTime = LocalDateTime.now();
        }
        if (!pickupTime.isBefore(deliveryTime)) {
            throw new IllegalStateException("Pickup time is not prior to the delivery time");
        }


        if (RateCard.dayHours(pickupTime)) {
            return calculatePrice4PickupInTheDay(longHaulRate, sequentialDistances);
        } else {
            return calculatePrice4PickupInTheNight(longHaulRate, sequentialDistances);
        }

    }


    protected BigDecimal calculatePrice4PickupInTheDay(boolean longHaulRate, List<Double> sequentialDistances) {
        RateCard rateCard = RateCard.getDayRate();
        BigDecimal price = BigDecimal.valueOf(rateCard.getInitCharge())
                .setScale(6, RoundingMode.HALF_UP);

        double totalDistances = this.getAccumulatedDistances(sequentialDistances, sequentialDistances.size() - 1);

        if (initHaulage(totalDistances)) {
            return price;
        }

        for (int i = 0; i < sequentialDistances.size(); i++) {
            if (i % 2 == 1) {
                rateCard = RateCard.getNightRate();
            } else {
                rateCard = RateCard.getDayRate();
            }

            price = accumulatePrice(price, longHaulRate, rateCard, sequentialDistances, i);
        }

        return price;
    }

    private BigDecimal accumulatePrice(BigDecimal price, boolean longHaulRate, RateCard rateCard, List<Double> sequentialDistances, int i) {
        double accumulatedDistances = this.getAccumulatedDistances(sequentialDistances, i);
        double precumulatedDistances = this.getAccumulatedDistances(sequentialDistances, i - 1);

        if (longHaulage(accumulatedDistances) && longHaulRate) {
            double unitPrice = rateCard.getLongHaulageCharge();
            double theDistance = sequentialDistances.get(i);
            if (precumulatedDistances > LONG_DISTANCE) {
                price = price.add(BigDecimal.valueOf(unitPrice * theDistance)
                        .setScale(6, RoundingMode.HALF_UP));
            } else {
                if (accumulatedDistances > LONG_DISTANCE) {
                    theDistance = accumulatedDistances;
                }
                price = price.add(BigDecimal.valueOf(rateCard.getUnitCharge() * (LONG_DISTANCE - INIT_DISTANCE)
                        + unitPrice * (theDistance - LONG_DISTANCE))
                        .setScale(6, RoundingMode.HALF_UP));
            }
        } else {
            double unitPrice = rateCard.getUnitCharge();
            double theDistance = sequentialDistances.get(i);
            if (accumulatedDistances > INIT_DISTANCE) {
                theDistance = accumulatedDistances;
            }
            if (theDistance > INIT_DISTANCE) {
                price = price.add(BigDecimal.valueOf(unitPrice * (theDistance - INIT_DISTANCE))
                        .setScale(6, RoundingMode.HALF_UP));
            }
        }
        return price;
    }

    private BigDecimal calculatePrice4PickupInTheNight(boolean longHaulRate, List<Double> sequentialDistances) {
        RateCard rateCard = RateCard.getNightRate();
        BigDecimal price = BigDecimal.valueOf(rateCard.getInitCharge()).setScale(6, RoundingMode.HALF_UP);

        double totalDistances = this.getAccumulatedDistances(sequentialDistances, sequentialDistances.size() - 1);

        if (initHaulage(totalDistances)) {
            return price;
        }

        for (int i = 0; i < sequentialDistances.size(); i++) {
            if (i % 2 == 1) {
                rateCard = RateCard.getDayRate();
            } else {
                rateCard = RateCard.getNightRate();
            }

            price = accumulatePrice(price, longHaulRate, rateCard, sequentialDistances, i);
        }

        return price;
    }

    public LocalDateTime getPickupTime() {
        return this.pickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    protected void setSequentialDistances(List<Double> sequentialDistances) {
        this.sequentialDistances = sequentialDistances;
    }

    protected double getAccumulatedDistances(List<Double> sequentialDistances, int i) {
        if (sequentialDistances == null || sequentialDistances.isEmpty()) {
            return 0;
        }
        double accumulatedDistance = 0;
        for (int j = 0; j <= i; j++) {
            accumulatedDistance += sequentialDistances.get(j);
        }
        return accumulatedDistance;
    }

}
