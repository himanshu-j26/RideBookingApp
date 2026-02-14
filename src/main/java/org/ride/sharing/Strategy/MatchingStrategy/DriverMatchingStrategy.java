package org.ride.sharing.Strategy.MatchingStrategy;

import org.ride.sharing.Entities.Driver;
import org.ride.sharing.Entities.Location;
import org.ride.sharing.Enums.RideType;

import java.util.List;

public interface DriverMatchingStrategy {
    Driver findDrivers(List<Driver> allDrivers, Location pickup, RideType rideType);
}
