package com.parking.parkinglot.strategy;

import com.parking.parkinglot.exception.ParkingException;
import com.parking.parkinglot.exception.ParkingExceptionCode;
import com.parking.parkinglot.model.IVehicle;
import com.parking.parkinglot.model.ParkingSpot;
import com.parking.parkinglot.model.ParkingType;
import com.parking.parkinglot.model.VehicleInfo;
import com.parking.parkinglot.parking.MultiLevelParking;
import com.parking.parkinglot.parking.ParkingArea;
import javafx.util.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingStrategy {

    public Pair<Integer, ParkingSpot> assignVehicle(MultiLevelParking multiLevelParking, boolean isElderly, IVehicle vehicle) throws ParkingException {
        List<ParkingArea> parking = multiLevelParking.getParkingAreas();
        Pair<Integer, ParkingSpot> pair = getAvailableSpot(parking, isElderly, vehicle.getType());
        ParkingSpot spot = pair.getValue();

        if(null == spot && vehicle.getType() == ParkingType.BIKE){
            pair = getAvailableSpot(parking, isElderly, ParkingType.CAR);
            spot = pair.getValue();
        }
        spot.addVehicle(vehicle);
        return pair;
    }

    private Pair<Integer, ParkingSpot> getAvailableSpot(List<ParkingArea> parking, boolean isElderly, ParkingType type) throws ParkingException {
        int j = parking.size();
        if(j > 1){
            j = isElderly ? 0 : 1;
        }else{
            j=0;
        }
        for(int i = j; i<parking.size(); i++){
            ParkingArea area = parking.get(i);
            ParkingSpot spot;
            try {
                spot = area.getParkingSpot(type);
            } catch (ParkingException e) {
                continue;
            }

            if(spot.freeSpots()){
                return new Pair<>(i, spot);
            }
        }

        throw new ParkingException(ParkingExceptionCode.NO_PARKING);
    }

    public ParkingSpot removeVehicle(MultiLevelParking multiLevelParking, VehicleInfo info, String vehicleNumber) throws ParkingException {
        List<ParkingArea> parking = multiLevelParking.getParkingAreas();
        if(info.getSpot().getSpotType() != ParkingType.ROYAL){
            ParkingSpot spot = parking.get(info.getParkingLevel()).getParkingSpot(info.getSpot().getSpotNumber());
            List<IVehicle> vehicles = info.getSpot().getVehicles().stream().filter(v -> v.getNumber().equalsIgnoreCase(vehicleNumber)).collect(Collectors.toList());
            spot.removeVehicle(vehicles.get(0));
            return spot;
        }else{
            return removeRoyalSpots(parking.get(info.getParkingLevel()).getParkingSpots(), info);
        }
    }

    private ParkingSpot removeRoyalSpots(ParkingSpot[][] parkingSpots, VehicleInfo info){
        int i = info.getSpot().getRow();
        int j = info.getSpot().getCol();

        if(i==0 || i == parkingSpots.length-1){
            if(j == 0){
                parkingSpots[i][j+1] = new ParkingSpot(i,j+1);
            }else if(j == parkingSpots[i].length-1){
                parkingSpots[i][j-1]  = new ParkingSpot(i,j-1);
            }else{
                parkingSpots[i][j+1] = new ParkingSpot(i,j+1);
                parkingSpots[i][j-1] = new ParkingSpot(i,j-1);
            }
        }else if(i==1){
            if(j == 0){
                parkingSpots[i][j+1] = new ParkingSpot(i,j+1);
                parkingSpots[i+1][j+1] = new ParkingSpot(i+1,j+1);
                parkingSpots[i+1][j] = new ParkingSpot(i+1,j);
            }else if(j == parkingSpots[i].length-1){
                parkingSpots[i][j-1] = new ParkingSpot(i,j-1);
                parkingSpots[i+1][j+1] = new ParkingSpot(i+1,j+1);
                parkingSpots[i+1][j] = new ParkingSpot(i+1,j);
            }else{
                parkingSpots[i][j+1] = new ParkingSpot(i,j+1);
                parkingSpots[i][j-1] = new ParkingSpot(i,j-1);
                parkingSpots[i+1][j+1] = new ParkingSpot(i+1,j+1);
                parkingSpots[i+1][j-1] = new ParkingSpot(i+1,j-1);
                parkingSpots[i+1][j] = new ParkingSpot(i+1,j);
            }
        }else if(i==parkingSpots.length-2){
            if(j == 0){
                parkingSpots[i][j+1] = new ParkingSpot(i,j+1);
                parkingSpots[i-1][j+1] = new ParkingSpot(i-1,j+1);
                parkingSpots[i-1][j] = new ParkingSpot(i-1,j);
            }else if(j == parkingSpots[i].length-1){
                parkingSpots[i][j-1] = new ParkingSpot(i,j-1);
                parkingSpots[i-1][j-1] = new ParkingSpot(i-1,j-1);
                parkingSpots[i-1][j] = new ParkingSpot(i-1,j);
            }else{
                parkingSpots[i][j+1] = new ParkingSpot(i,j+1);
                parkingSpots[i][j-1] = new ParkingSpot(i,j-1);
                parkingSpots[i-1][j+1] = new ParkingSpot(i-1,j+1);
                parkingSpots[i-1][j-1] = new ParkingSpot(i-1,j-1);
                parkingSpots[i-1][j] = new ParkingSpot(i-1,j);
            }
        }

        parkingSpots[i][j] = new ParkingSpot(i,j);
        return parkingSpots[i][j];
    }

    public String getParkingInfo(MultiLevelParking multiLevelParking){
        List<ParkingArea> parking = multiLevelParking.getParkingAreas();
        return parking.toString();
    }
}
