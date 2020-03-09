package com.parking.parkinglot.model;

public class VehicleInfo {

    int parkingLevel;
    ParkingSpot spot;

    public int getParkingLevel() {
        return parkingLevel;
    }

    public void setParkingLevel(int parkingLevel) {
        this.parkingLevel = parkingLevel;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public void setSpot(ParkingSpot spot) {
        this.spot = spot;
    }
}
