package org.ride.sharing.States;

import org.ride.sharing.Entities.Driver;
import org.ride.sharing.Entities.Trip;
import org.ride.sharing.Enums.TripStatus;

public class InProgressState implements TripState{
    @Override
    public void request(Trip trip) {
        System.out.println("Trip has already been requested and assigned.");
    }

    @Override
    public void assign(Trip trip, Driver driver) {
        System.out.println("Driver is already assigned");
    }

    @Override
    public void start(Trip trip) {
        System.out.println("Trip has already started");
    }

    @Override
    public void end(Trip trip) {
        trip.setTripState(new CompletedState());
    }

    @Override
    public TripStatus getStatus() {
        return TripStatus.IN_PROGRESS;
    }
}
