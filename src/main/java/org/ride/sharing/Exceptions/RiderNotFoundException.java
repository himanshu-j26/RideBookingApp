package org.ride.sharing.Exceptions;

public class RiderNotFoundException extends RuntimeException {
    public RiderNotFoundException(String message) {
        super(message);
    }
}
