package com.parking.parkinglot.parking;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.parking.parkinglot.exception.ParkingException;
import com.parking.parkinglot.model.ParkingSpot;
import com.parking.parkinglot.model.ParkingType;
import org.junit.jupiter.api.Test;

public class ParkingAreaTest {

    @Test
    public void checkSpotNumber(){
        int level = 1;
        ParkingSpot[][] expectedSpots = new ParkingSpot[4][5];
        expectedSpots[1][3] = new ParkingSpot(1,3);
        expectedSpots[3][2] = new ParkingSpot(3,2);
        ParkingArea area = new ParkingArea(level);

        assertTrue(area.getParkingSpots()[1][3].getSpotNumber() == expectedSpots[1][3].getSpotNumber());
        assertTrue(area.getParkingSpots()[3][2].getSpotNumber() == expectedSpots[3][2].getSpotNumber());

    }

    @Test
    public void getParkingSpotWithSpotNumber() throws ParkingException {
        int level = 1;
        int expectedRow = 0;
        int expectedCol = 4;

        ParkingArea area = new ParkingArea(level);
        ParkingSpot spot = area.getParkingSpot(5);
        int row = spot.getRow();
        int col = spot.getCol();

        assertTrue(expectedRow == row);
        assertTrue(expectedCol == col);
    }

    @Test
    public void getParkingSpotWithNoSpotNumber() throws ParkingException{
        assertThrows(ParkingException.class, () -> {
            int level = 1;
            int expectedRow = 0;
            int expectedCol = 4;

            ParkingArea area = new ParkingArea(level);
            ParkingSpot spot = area.getParkingSpot(34);
        });
    }

    @Test
    public void getParkingSpotWithSpotType() throws ParkingException {
        int level = 1;
        int carRow = 0;
        int carCol = 0;
        int bikeRow = 0;
        int bikeCol = 1;

        ParkingArea area = new ParkingArea(level);
        ParkingSpot carSpot = area.getParkingSpot(ParkingType.CAR);

        assertTrue(carRow == carSpot.getRow());
        assertTrue(carCol == carSpot.getCol());

        ParkingSpot bikeSpot = area.getParkingSpot(ParkingType.BIKE);

        assertTrue(bikeRow == bikeSpot.getRow());
        assertTrue(bikeCol == bikeSpot.getCol());
    }

    @Test
    public void getRoyalSpot() throws ParkingException {
        int level = 1;
        int royalRow = 0;
        int royalCol = 3;

        ParkingArea area = new ParkingArea(level);
        ParkingSpot carSpot = area.getParkingSpot(ParkingType.CAR);

        ParkingSpot bikeSpot = area.getParkingSpot(ParkingType.BIKE);

        ParkingSpot royalSpot = area.getParkingSpot(ParkingType.ROYAL);

        assertTrue(royalRow == royalSpot.getRow());
        assertTrue(royalCol == royalSpot.getCol());
    }
}
