package com.parking.parkinglot.parking;

import com.parking.parkinglot.exception.ParkingException;
import com.parking.parkinglot.exception.ParkingExceptionCode;
import com.parking.parkinglot.model.ParkingSpot;
import com.parking.parkinglot.model.ParkingType;

public class ParkingArea {

    private ParkingSpot[][] parkingSpots = new ParkingSpot[4][5];
    private int parkingLevel;


    public ParkingArea(int level){
        this.parkingLevel = level;
        for (int i =0; i<parkingSpots.length; i++) {
            for (int j =0; j<parkingSpots[i].length; j++){
                parkingSpots[i][j] = new ParkingSpot(i,j);
            }
        }
    }

    public ParkingSpot[][] getParkingSpots() {
        return parkingSpots;
    }

    public ParkingSpot getParkingSpot(int spotNumber) throws ParkingException {

        for (int i =0; i<parkingSpots.length; i++) {
            for (int j =0; j<parkingSpots[i].length; j++){
                if(parkingSpots[i][j].getSpotNumber() == spotNumber){
                    return parkingSpots[i][j];
                }
            }
        }
        throw new ParkingException(ParkingExceptionCode.NO_SPOT_NUMBER);
    }

    public ParkingSpot getParkingSpot(ParkingType type) throws ParkingException {
        if(type == ParkingType.ROYAL){
            ParkingSpot spot = getRoyalParkingSpot();
            spot.setSpotType(ParkingType.ROYAL);
            return spot;
        }
        for (int i =0; i<parkingSpots.length; i++) {
            for (int j =0; j<parkingSpots[i].length; j++){

                if(null == parkingSpots[i][j].getSpotType()){
                    parkingSpots[i][j].setSpotType(type);
                    return parkingSpots[i][j];
                }
                if(type == parkingSpots[i][j].getSpotType() && parkingSpots[i][j].freeSpots()){
                    return  parkingSpots[i][j];
                }
            }
        }
        throw new ParkingException(ParkingExceptionCode.NO_PARKING);
    }

    private ParkingSpot getRoyalParkingSpot() throws ParkingException {
        for (int i =0; i<parkingSpots.length; i++) {
            for (int j = 0; j < parkingSpots[i].length; j++) {
                if(i==0 || i == parkingSpots.length-1){
                    if(j == 0){
                        if(null == parkingSpots[i][j+1].getSpotType()){
                            parkingSpots[i][j+1].setSpotType(ParkingType.RESERVED);
                            return parkingSpots[i][j];
                        }
                    }else if(j == parkingSpots[i].length-1){
                        if(null == parkingSpots[i][j-1].getSpotType()){
                            parkingSpots[i][j-1].setSpotType(ParkingType.RESERVED);
                            return parkingSpots[i][j];
                        }
                    }else{
                        if(null == parkingSpots[i][j+1].getSpotType() && null == parkingSpots[i][j-1].getSpotType()){
                            parkingSpots[i][j+1].setSpotType(ParkingType.RESERVED);
                            parkingSpots[i][j-1].setSpotType(ParkingType.RESERVED);
                            return parkingSpots[i][j];
                        }
                    }
                }else if(i==1){
                    if(j == 0){
                        if(null == parkingSpots[i][j+1].getSpotType() && null == parkingSpots[i+1][j+1].getSpotType() && null == parkingSpots[i+1][j].getSpotType()){
                            parkingSpots[i][j+1].setSpotType(ParkingType.RESERVED);
                            parkingSpots[i+1][j+1].setSpotType(ParkingType.RESERVED);
                            parkingSpots[i+1][j].setSpotType(ParkingType.RESERVED);
                            return parkingSpots[i][j];
                        }
                    }else if(j == parkingSpots[i].length-1){
                        if(null == parkingSpots[i][j-1].getSpotType() && null == parkingSpots[i+1][j-1].getSpotType() && null == parkingSpots[i+1][j].getSpotType()){
                            parkingSpots[i][j-1].setSpotType(ParkingType.RESERVED);
                            parkingSpots[i+1][j+1].setSpotType(ParkingType.RESERVED);
                            parkingSpots[i+1][j].setSpotType(ParkingType.RESERVED);
                            return parkingSpots[i][j];
                        }
                    }else{
                        if(null == parkingSpots[i][j+1].getSpotType() && null == parkingSpots[i][j-1].getSpotType() && null == parkingSpots[i+1][j+1].getSpotType() &&
                                null == parkingSpots[i+1][j-1].getSpotType() && null == parkingSpots[i+1][j].getSpotType()){
                            parkingSpots[i][j+1].setSpotType(ParkingType.RESERVED);
                            parkingSpots[i][j-1].setSpotType(ParkingType.RESERVED);
                            parkingSpots[i+1][j+1].setSpotType(ParkingType.RESERVED);
                            parkingSpots[i+1][j-1].setSpotType(ParkingType.RESERVED);
                            parkingSpots[i+1][j].setSpotType(ParkingType.RESERVED);

                            return parkingSpots[i][j];
                        }
                    }
                }else if(i==parkingSpots.length-2){
                    if(j == 0){
                        if(null == parkingSpots[i][j+1].getSpotType() && null == parkingSpots[i-1][j+1].getSpotType() && null == parkingSpots[i-1][j].getSpotType()){
                            parkingSpots[i][j+1].setSpotType(ParkingType.RESERVED);
                            parkingSpots[i-1][j+1].setSpotType(ParkingType.RESERVED);
                            parkingSpots[i-1][j].setSpotType(ParkingType.RESERVED);
                            return parkingSpots[i][j];
                        }
                    }else if(j == parkingSpots[i].length-1){
                        if(null == parkingSpots[i][j-1].getSpotType() && null == parkingSpots[i-1][j-1].getSpotType() && null == parkingSpots[i-1][j].getSpotType()){
                            parkingSpots[i][j-1].setSpotType(ParkingType.RESERVED);
                            parkingSpots[i-1][j-1].setSpotType(ParkingType.RESERVED);
                            parkingSpots[i-1][j].setSpotType(ParkingType.RESERVED);
                            return parkingSpots[i][j];
                        }
                    }else{
                        if(null == parkingSpots[i][j+1].getSpotType() && null == parkingSpots[i][j-1].getSpotType() && null == parkingSpots[i-1][j+1].getSpotType() &&
                                null == parkingSpots[i-1][j-1].getSpotType() && null == parkingSpots[i-1][j].getSpotType()){
                            parkingSpots[i][j+1].setSpotType(ParkingType.RESERVED);
                            parkingSpots[i][j-1].setSpotType(ParkingType.RESERVED);
                            parkingSpots[i-1][j+1].setSpotType(ParkingType.RESERVED);
                            parkingSpots[i-1][j-1].setSpotType(ParkingType.RESERVED);
                            parkingSpots[i-1][j].setSpotType(ParkingType.RESERVED);
                            return parkingSpots[i][j];
                        }
                    }
                }
            }
        }

        throw new ParkingException(ParkingExceptionCode.NO_ROYAL_PARKING);
    }

    public int getParkingLevel() {
        return parkingLevel;
    }

    @Override
    public String toString() {
        String spots = "";
        for (int i = 0; i < parkingSpots.length; i++){
            for (int j = 0; j < parkingSpots[i].length; j++){
                spots = spots+ parkingSpots[i][j] + "\n";
            }
        }
        return "ParkingArea{" +
                "parkingSpots=" + "\n" + spots +
                ", parkingLevel=" + parkingLevel +
                '}' + "\n";
    }
}
