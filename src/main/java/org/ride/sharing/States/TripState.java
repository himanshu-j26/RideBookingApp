package org.ride.sharing.States;

import org.ride.sharing.Entities.Driver;
import org.ride.sharing.Entities.Trip;

public interface TripState {
    void request(Trip trip);
    void assign(Trip trip, Driver driver);
    void start(Trip trip);
    void end(Trip trip);
}
