package com.parking.parkinglot.model;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingSpot {

    private List<IVehicle> vehicles = new ArrayList<IVehicle>();
    private ParkingType spotType;
    private int spotNumber;
    private int row;
    private int col;

    public ParkingSpot(int row, int col){
        this.row = row;
        this.col = col;
        this.spotNumber = row*4 + row+col+1;
    }

    public void addVehicle(IVehicle vehicle){
      this.vehicles.add(vehicle);
    }

    public void removeVehicle(IVehicle vehicle){
        vehicles.remove(vehicle);
        if( vehicles.size() ==0){
            spotType = null;
        }
        if(spotType == ParkingType.MIX){
            int bikeNum = vehicles.stream().filter(v -> v.getType() == ParkingType.BIKE).collect(Collectors.toList()).size();
            if(bikeNum == vehicles.size()){
                spotType = ParkingType.BIKE;
            }else if(bikeNum == 0){
                spotType = ParkingType.CAR;
            }
        }

    }

    public boolean freeSpots(){
        if(null == spotType) return false;
        switch (spotType){
            case CAR:
                return vehicles.size() <2;
            case BIKE:
                return vehicles.size() <5;
            case ROYAL:
                return vehicles.size() <1;
            case MIX:
                return vehicles.size() <3;
            default:
                return vehicles.size() == 0;
        }
    }


    public ParkingType getSpotType() {
        return spotType;
    }

    public void setSpotType(ParkingType spotType) {
        this.spotType = spotType;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public List<IVehicle> getVehicles() {
        return vehicles;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "ParkingSpot: " + "vehicles=" + vehicles + ", spotType=" + spotType + ", spotNumber=" + spotNumber;
    }
}
