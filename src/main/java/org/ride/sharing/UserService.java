package org.ride.sharing;

import org.ride.sharing.Entities.Driver;
import org.ride.sharing.Entities.Location;
import org.ride.sharing.Entities.Rider;
import org.ride.sharing.Entities.Vehicle;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {
    private final Map<String, Rider> allRiders = new ConcurrentHashMap<>();
    private final Map<String, Driver> allDrivers = new ConcurrentHashMap<>();

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

    public Driver getDriverFromDriverId(String driverId){
        return allDrivers.get(driverId);
    }

    public Rider getRiderFromRiderId(String riderId){
        return allRiders.get(riderId);
    }

    public List<Rider>  getAllRiders() {
        return List.copyOf(allRiders.values());
    }

    public List<Driver> getAllDrivers() {
        return List.copyOf(allDrivers.values());
    }
}
