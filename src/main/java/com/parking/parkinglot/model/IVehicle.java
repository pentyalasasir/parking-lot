package com.parking.parkinglot.model;

public abstract class IVehicle {

    private ParkingType type;
    private String number;

    public IVehicle(ParkingType type, String number) {
        this.type = type;
        this.number = number;
    }

    public ParkingType getType() {
        return type;
    }

    public void setType(ParkingType type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "{ type= " + type + ", number='" + number + " }";
    }
}
