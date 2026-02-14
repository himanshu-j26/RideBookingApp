package org.ride.sharing.Strategy.MatchingStrategy;

import org.ride.sharing.Entities.Driver;
import org.ride.sharing.Entities.Location;
import org.ride.sharing.Enums.DriverStatus;
import org.ride.sharing.Enums.RideType;

import java.util.Comparator;
import java.util.List;

public class NearestDriverStrategy implements DriverMatchingStrategy{
    private static final double MAX_DISTANCE = 5.0;
    @Override
    public Driver findDrivers(List<Driver> allDrivers, Location pickup, RideType rideType) {
        System.out.println("Finding nearby drivers");
        List<Driver> sortedDrivers =  allDrivers.stream()
                .filter(driver -> driver.getDriverStatus() == DriverStatus.ONLINE)
                .filter(driver -> driver.getVehicle().getRideType() == rideType)
                .filter(driver -> pickup.getDistance(driver.getCurrentLocation()) < MAX_DISTANCE)
                .sorted(Comparator.comparingDouble(driver -> pickup.getDistance(driver.getCurrentLocation())))
                .toList();
        for (Driver driver : sortedDrivers) {
            if (driver.tryReserve()) {
                return driver;
            }
        }
        return null;
    }
}
