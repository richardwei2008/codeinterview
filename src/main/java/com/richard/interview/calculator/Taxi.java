package com.richard.interview.calculator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Taxi {

    private ServiceArea serviceArea;

    private Meter meter;

    public Taxi(ServiceArea serviceArea) {
        this.serviceArea = serviceArea;
        this.meter = new Meter();
    }

    private BigDecimal calculatePrice() {
        if (null == meter || null == meter.getPickupTime()) {
            throw new IllegalStateException("Taxi is out of service");
        }
        return meter.currentPrice(serviceArea == ServiceArea.DOWNTOWN);
    }

    public void pickup(LocalDateTime pickupTime) {
        meter.setPickupTime(pickupTime);
        meter.setDeliveryTime(null);
    }

    public BigDecimal deliverToDestination(LocalDateTime deliveryTime) {
        meter.setDeliveryTime(deliveryTime);
        return this.calculatePrice();
    }

}
