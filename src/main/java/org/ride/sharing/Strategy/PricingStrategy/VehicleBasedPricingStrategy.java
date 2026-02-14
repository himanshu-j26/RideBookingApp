package org.ride.sharing.Strategy.PricingStrategy;

import org.ride.sharing.Entities.Location;
import org.ride.sharing.Enums.RideType;

import java.util.Map;

public class VehicleBasedPricingStrategy implements PricingStrategy{
    private static final double BASE_PAY = 100;
    private static final Map<RideType, Double> RATE_PER_KM = Map.of(
            RideType.SUV, 20.0,
            RideType.SEDAN, 30.0,
            RideType.AUTO, 10.0
    );
    @Override
    public double calculatePrice(Location pickup, Location drop, RideType rideType) {
        return BASE_PAY + RATE_PER_KM.get(rideType) * pickup.getDistance(drop);
    }
}
