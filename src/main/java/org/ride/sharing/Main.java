package org.ride.sharing;

import org.ride.sharing.Entities.*;
import org.ride.sharing.Enums.DriverStatus;
import org.ride.sharing.Enums.RideType;
import org.ride.sharing.Strategy.MatchingStrategy.NearestDriverStrategy;
import org.ride.sharing.Strategy.PricingStrategy.VehicleBasedPricingStrategy;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       RideSharingService sharingService = RideSharingService.getInstance();
       sharingService.setPricingStrategy(new VehicleBasedPricingStrategy());
       sharingService.setDriverMatchingStrategy(new NearestDriverStrategy());

        Rider rider1 = sharingService.registerRider("Rider1", "123456");

        Driver driver1 = sharingService.registerDriver(
                "Driver1", "34567",
                new Vehicle("KA01-1234", "Toyota Prius", RideType.SEDAN),
                new Location(1.0, 1.0));

        Driver driver2 = sharingService.registerDriver(
                "Driver2", "34567",
                new Vehicle("KA02-1234", "Tata Nexon", RideType.SUV),
                new Location(2.0, 7.0));

        Driver driver3 = sharingService.registerDriver(
                "Driver3", "34567",
                new Vehicle("KA03-1234", "Hyundai Verna", RideType.SEDAN),
                new Location(3.0, 3.0));

        Driver driver4 = sharingService.registerDriver(
                "Driver4", "34567",
                new Vehicle("KA04-1234", "Bajaj Auto", RideType.AUTO),
                new Location(4.0, 4.0));

        driver1.setDriverStatus(DriverStatus.ONLINE);
        driver2.setDriverStatus(DriverStatus.ONLINE);
        driver3.setDriverStatus(DriverStatus.ONLINE);
        driver4.setDriverStatus(DriverStatus.ONLINE);

        Location pickup = new Location(0.0, 0.0);
        Location drop = new Location(10.0, 10.0);

        Trip trip1 = sharingService.requestTrip(rider1.getId(), pickup, drop, RideType.SEDAN);

        if(trip1 != null) {
            sharingService.acceptTrip(trip1.getId());
            sharingService.startTrip(trip1.getId());
            sharingService.endTrip(trip1.getId());
        }

        System.out.println("\n--- Checking Trip History ---");
        System.out.println("Rider1's trip history: " + rider1.getPastTrips());

        Rider rider2 = sharingService.registerRider("Rider2", "123456");
        Trip trip2 = sharingService.requestTrip(
                rider2.getId(),
                new Location(2.0, 2.0),
                new Location(6.0, 8.0),
                RideType.AUTO);

        if(trip2 != null) {
            sharingService.acceptTrip(trip2.getId());
            sharingService.startTrip(trip2.getId());
            sharingService.endTrip(trip2.getId());
        }

        Trip trip3 = sharingService.requestTrip(
                rider1.getId(),
                new Location(8.0, 9.0),
                new Location(1.0, 2.0),
                RideType.SEDAN
        );

        if(trip3 != null) {
            sharingService.acceptTrip(trip3.getId());
            sharingService.startTrip(trip3.getId());
            sharingService.endTrip(trip3.getId());
        }

        System.out.println("\n--- Checking Trip History ---");
        System.out.println("Rider1's trip history. Completed Trips: " + rider1.getPastTrips());
    }
}