package com.parking.parkinglot.parking;

import java.util.ArrayList;
import java.util.List;

public class MultiLevelParking {

    private List<ParkingArea> parkingAreas = new ArrayList<>();

    public void intializeMultiParking(int levels){
        for(int i=0; i<levels; i++){
            parkingAreas.add(new ParkingArea(i));
        }

    }

    public List<ParkingArea> getParkingAreas() {
        return parkingAreas;
    }
}
