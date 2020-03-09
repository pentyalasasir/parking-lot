package com.parking.parkinglot.exception;

public enum ParkingExceptionCode {
    NO_ROYAL_PARKING("001", "No Royal Parking available"),
    NO_PARKING("002","No Parking available"),
    NO_SPOT_NUMBER("003","No Parking available");


    private final String id;
    private final String message;

    ParkingExceptionCode(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return this.id;
    }

    public String getMessage() {
        return this.message;
    }
}
