package org.ride.sharing;

import org.ride.sharing.Entities.*;
import org.ride.sharing.Enums.DriverStatus;
import org.ride.sharing.Enums.RideType;
import org.ride.sharing.Exceptions.DriverNotFoundException;
import org.ride.sharing.Exceptions.NoTripFoundException;
import org.ride.sharing.Exceptions.RiderNotFoundException;
import org.ride.sharing.Strategy.MatchingStrategy.DriverMatchingStrategy;
import org.ride.sharing.Strategy.PricingStrategy.PricingStrategy;

import java.util.List;

public class RideSharingService {
    private final UserService userService;
    private final TripService tripService;
    private final DriverMatchingStrategy driverMatchingStrategy;
    private final PricingStrategy pricingStrategy;

    public RideSharingService(UserService userService,
                              TripService tripService,
                              DriverMatchingStrategy matchingStrategy,
                              PricingStrategy pricingStrategy) {
        this.userService = userService;
        this.tripService = tripService;
        this.driverMatchingStrategy = matchingStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public Driver registerDriver(
            String name, String contact, Vehicle vehicle, Location initialLocation) {
        return userService.registerDriver(name, contact, vehicle, initialLocation);
    }

    public Rider registerRider(String name, String contact) {
        return userService.registerRider(name, contact);
    }

    public Trip requestTrip(String riderId, Location pickup, Location drop, RideType rideType) {
        Rider rider = userService.getRiderFromRiderId(riderId);
        if(rider == null) {
            System.err.println("User does not exist");
            throw new RiderNotFoundException("Rider does not exist");
        }

        System.out.println("New ride request by rider: "+ rider.getName());
        System.out.println("Waiting for drivers to accept this trip");
        Driver driver = driverMatchingStrategy.findDrivers(List.copyOf(userService.getAllDrivers()), pickup, rideType);

        if(driver == null) {
            System.err.println("Not able to find available drivers nearby");
            return null;
        }

        System.out.println("Found driver for the ride: "+ driver.getName());
        double fee = pricingStrategy.calculatePrice(pickup, drop, rideType);
        System.out.println("Estimated fare: " + fee);

        Trip trip = tripService.createTrip(pickup, drop, fee, rider);

        trip.assignDriver(driver);
        return trip;
    }

    // This class is redundant as drivers are accepting inside DriverMatchingStrategy while requesting
    public void acceptTrip(String tripId) {
        Trip trip = tripService.getTrip(tripId);
        if(trip == null) {
            throw new NoTripFoundException("No trip found while accepting");
        }

        Driver driver = trip.getDriver();
        if(driver == null) {
            throw new DriverNotFoundException("No driver found while accepting");
        }

        driver.setDriverStatus(DriverStatus.IN_TRIP);
        trip.assignDriver(driver);
        System.out.println("Driver " + driver.getName() + " is starting ride with rider "+ trip.getRider().getName());
    }

    public void startTrip(String tripId) {
        Trip trip = tripService.getTrip(tripId);

        if(trip == null) {
            throw new NoTripFoundException("Trip not found");
        }

        System.out.println("Trip is starting");
        trip.startTrip(trip);
    }

    public void endTrip(String tripId) {
        Trip trip = tripService.getTrip(tripId);

        if(trip == null) {
            throw new NoTripFoundException("Trip not found");
        }

        System.out.println("Trip is ending");
        trip.endTrip(trip);

        Driver driver = trip.getDriver();
        driver.setDriverStatus(DriverStatus.ONLINE);
        driver.setCurrentLocation(trip.getDropLocation());

        Rider rider = trip.getRider();
        rider.addTrip(trip);
        driver.addTrip(trip);

        System.out.println("Driver " + driver.getName() + " is now back online at " + driver.getCurrentLocation());
    }
}
