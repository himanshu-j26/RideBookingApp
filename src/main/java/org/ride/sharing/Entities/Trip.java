package org.ride.sharing.Entities;

import org.ride.sharing.States.RequestedState;
import org.ride.sharing.States.TripState;

import java.util.UUID;

public class Trip {
    private final String id;
    private Driver Driver;
    private final Location pickupLocation;
    private final Location dropLocation;
    private final double fare;
    private final Rider rider;

    private TripState tripState;

    public Trip(Location pickupLocation, Location dropLocation, double fair, Rider rider) {
        this.id = UUID.randomUUID().toString();
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.fare = fair ;
        this.rider = rider;
        this.tripState = new RequestedState();
    }

    public synchronized void assignDriver(Driver driver) {
        this.tripState.assign(this, driver);
    }

    public void startTrip(Trip trip) {
        this.tripState.start(trip);
    }

    public void endTrip(Trip trip) {
        this.tripState.end(trip);
    }

    public String getId() {
        return id;
    }

    public Driver getDriver() {
        return Driver;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public Location getDropLocation() {
        return dropLocation;
    }

    public double getFare() {
        return fare;
    }

    public TripState getTripState() {
        return tripState;
    }

    public Rider getRider() {
        return rider;
    }

    public void setDriver(Driver driver) {
        Driver = driver;
    }

    public void setTripState(TripState tripState) {
        this.tripState = tripState;
    }

    @Override
    public String toString() {
        return "Trip [id=" + id + ", fare=$" + String.format("%.2f", fare) + "]";
    }
}
