package org.ride.sharing.Entities;

import org.ride.sharing.Enums.RideType;

public class Vehicle {
    private final String licenceNumber;
    private final String model;
    private final RideType rideType;

    public Vehicle(String licenceNumber, String model, RideType rideType) {
        this.licenceNumber = licenceNumber;
        this.model = model;
        this.rideType = rideType;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public String getModel() {
        return model;
    }

    public RideType getRideType() {
        return rideType;
    }
}
