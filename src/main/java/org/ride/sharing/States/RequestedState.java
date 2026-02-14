package org.ride.sharing.States;

import org.ride.sharing.Entities.Driver;
import org.ride.sharing.Entities.Trip;
import org.ride.sharing.Enums.TripStatus;

public class RequestedState implements TripState {
    @Override
    public void request(Trip trip) {
        System.out.println("Trip already in Requested state");
    }

    @Override
    public void assign(Trip trip, Driver driver) {
        trip.setTripState(new AssignedState());
        trip.setTripStatus(TripStatus.ASSIGNED);
        trip.setDriver(driver);
    }

    @Override
    public void start(Trip trip) {
        System.out.println("Can't start trip because driver has not assigned yet");
    }

    @Override
    public void end(Trip trip) {
        System.out.println("Can't end trip because it is not started yet");
    }
}
