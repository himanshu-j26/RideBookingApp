package org.ride.sharing.Exceptions;

public class NoTripFoundException extends RuntimeException {
    public NoTripFoundException(String message) {
        super(message);
    }
}
