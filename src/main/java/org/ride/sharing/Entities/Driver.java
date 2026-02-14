package org.ride.sharing.Entities;

import org.ride.sharing.Enums.DriverStatus;

public class Driver extends User{
    private Vehicle vehicle;
    private Location currentLocation;
    private DriverStatus driverStatus;

    public Driver(String name, String contact, Vehicle vehicle, Location currentLocation) {
        super(name, contact);
        this.vehicle = vehicle;
        this.currentLocation = currentLocation;
        this.driverStatus = DriverStatus.OFFLINE;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }
}
