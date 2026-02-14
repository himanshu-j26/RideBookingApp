package org.ride.sharing.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private final String id;
    private final String name;
    private final String contact;
    private final List<Trip> pastTrips;

    public User(String name, String contact) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.contact = contact;
        this.pastTrips = new ArrayList<>();
    }

    public List<Trip> getPastTrips() {
        return pastTrips;
    }

    public void addTrip(Trip trip) {
        pastTrips.add(trip);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}


