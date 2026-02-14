package org.ride.sharing;

import org.ride.sharing.Entities.Location;
import org.ride.sharing.Entities.Rider;
import org.ride.sharing.Entities.Trip;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TripService {
    private final Map<String, Trip> allTrips = new ConcurrentHashMap<>();

    public Trip createTrip(Location pickup, Location drop, double fee, Rider rider) {
        Trip trip = new Trip(pickup, drop, fee, rider);
        allTrips.put(trip.getId(), trip);
        return trip;
    }

    public Map<String, Trip> getAllTrips() {
        return allTrips;
    }

    public Trip getTrip(String tripId) {
        return allTrips.get(tripId);
    }
}
