package org.ride.sharing.Strategy.PricingStrategy;

import org.ride.sharing.Entities.Location;
import org.ride.sharing.Enums.RideType;

public interface PricingStrategy {
    double calculatePrice(Location pickup, Location drop, RideType rideType);
}
