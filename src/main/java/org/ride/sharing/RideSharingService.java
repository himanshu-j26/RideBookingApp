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
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

public class RideSharingService {
    public static RideSharingService INSTANCE;

    private final Map<String, Rider> allRiders = new ConcurrentHashMap<>();
    private final Map<String, Driver> allDrivers = new ConcurrentHashMap<>();
    private final Map<String, Trip> allTrips = new ConcurrentHashMap<>();

    private DriverMatchingStrategy driverMatchingStrategy;
    private PricingStrategy pricingStrategy;

    public RideSharingService() {}

    public static synchronized RideSharingService getInstance() {
        if(INSTANCE == null) {
            synchronized (RideSharingService.class) {
                if(INSTANCE == null) {
                INSTANCE = new RideSharingService();
                }
            }
        }
        return INSTANCE;
    }

    public void setDriverMatchingStrategy(DriverMatchingStrategy driverMatchingStrategy) {
        this.driverMatchingStrategy = driverMatchingStrategy;
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public Driver registerDriver(
            String name, String contact, Vehicle vehicle, Location initialLocation) {
        Driver driver = new Driver(name, contact, vehicle, initialLocation);
        allDrivers.put(driver.getId(), driver);
        return driver;
    }

    public Rider registerRider(String name, String contact) {
        Rider rider = new Rider(name, contact);
        allRiders.put(rider.getId(), rider);
        return rider;
    }

    public Trip requestTrip(String riderId, Location pickup, Location drop, RideType rideType) {
        Rider rider = allRiders.get(riderId);
        if(rider == null) {
            System.err.println("User does not exist");
            throw new RiderNotFoundException("Rider does not exist");
        }

        System.out.println("New ride request by rider: "+ rider.getName());

        List<Driver> drivers = driverMatchingStrategy.findDrivers(List.copyOf(allDrivers.values()), pickup, rideType);

        if(allDrivers.isEmpty()) {
            System.err.println("Not able to find available drivers nearby");
            return null;
        }

        System.out.println("Found " + allDrivers.size() + " available drivers");
        double fee = pricingStrategy.calculatePrice(pickup, drop, rideType);
        System.out.println("Estimated fare: " + fee);

        Trip trip = new Trip(pickup, drop, fee, rider);
        allTrips.put(trip.getId(), trip);
        System.out.println("Waiting for drivers to accept this trip");

        trip.setDriver(drivers.getFirst());
        return trip;
    }

    public void acceptTrip(String tripId) {
        Trip trip = allTrips.get(tripId);
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
        Trip trip = allTrips.get(tripId);

        if(trip == null) {
            throw new NoTripFoundException("Trip not found");
        }

        System.out.println("Trip is starting");
        trip.startTrip(trip);
    }

    public void endTrip(String tripId) {
        Trip trip = allTrips.get(tripId);

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
