package org.ride.sharing.Strategy.MatchingStrategy;

import org.ride.sharing.Entities.Driver;
import org.ride.sharing.Entities.Location;
import org.ride.sharing.Enums.DriverStatus;
import org.ride.sharing.Enums.RideType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NearestDriverStrategy implements DriverMatchingStrategy{
    private static final double MAX_DISTANCE = 5.0;
    @Override
    public List<Driver> findDrivers(List<Driver> allDrivers, Location pickup, RideType rideType) {
        System.out.println("Finding nearby drivers");
        return allDrivers.stream()
                .filter(driver -> driver.getDriverStatus() == DriverStatus.ONLINE)
                .filter(driver -> driver.getVehicle().getRideType() == rideType)
                .filter(driver -> pickup.getDistance(driver.getCurrentLocation()) < MAX_DISTANCE)
                .sorted(Comparator.comparingDouble(driver -> pickup.getDistance(driver.getCurrentLocation())))
                .toList();
    }
}
