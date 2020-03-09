package com.parking.parkinglot.exception;

public class ParkingException extends Exception {

    private static final long serialVersionUID = -743454409788681851L;
    private final String code;
    private final String description;

    public ParkingException(ParkingExceptionCode parkingExceptionCode) {
        super();
        this.code = parkingExceptionCode.getId();
        this.description = parkingExceptionCode.getMessage();
    }


    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
