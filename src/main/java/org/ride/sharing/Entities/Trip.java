package org.ride.sharing.Entities;

import org.ride.sharing.Enums.TripStatus;
import org.ride.sharing.States.RequestedState;
import org.ride.sharing.States.TripState;

import java.util.UUID;

public class Trip {
    private String id;
    private Driver Driver;
    private Location pickupLocation;
    private Location dropLocation;
    private double fare;
    private Rider rider;

    private TripStatus tripStatus;

    private TripState tripState;

    public Trip(Location pickupLocation, Location dropLocation, double fair, Rider rider) {
        this.id = UUID.randomUUID().toString();
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.fare = fair ;
        this.rider = rider;
        this.tripStatus = TripStatus.REQUESTED;
        this.tripState = new RequestedState();
    }

    public void assignDriver(Driver driver) {
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

    public TripStatus getTripStatus() {
        return tripStatus;
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

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public void setTripState(TripState tripState) {
        this.tripState = tripState;
    }

    @Override
    public String toString() {
        return "Trip [id=" + id + ", fare=$" + String.format("%.2f", fare) + "]";
    }
}
