package com.parking.parkinglot.strategy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.parking.parkinglot.exception.ParkingException;
import com.parking.parkinglot.model.*;
import com.parking.parkinglot.parking.MultiLevelParking;
import org.junit.jupiter.api.Test;

public class ParkingStrategyTest {

    @Test
    public void assignVehicleWhichIsNotRoyal() throws ParkingException {
        MultiLevelParking parking = new MultiLevelParking();
        parking.intializeMultiParking(1);
        IVehicle car = new Car(ParkingType.CAR, "KA12MH4567");
        ParkingStrategy strategy = new ParkingStrategy();

        ParkingSpot spot = strategy.assignVehicle(parking, false, car).getValue();

        assertTrue( spot.getVehicles().get(0).getNumber() == "KA12MH4567");
    }

    @Test
    public void assignRoyalVehicle() throws ParkingException {
        MultiLevelParking parking = new MultiLevelParking();
        parking.intializeMultiParking(1);
        IVehicle royalVehicle = new RoyalVehicle(ParkingType.ROYAL, "KA12MH4567");
        ParkingStrategy strategy = new ParkingStrategy();

        ParkingSpot spot = strategy.assignVehicle(parking, false, royalVehicle).getValue();

        ParkingSpot newSpot = parking.getParkingAreas().get(0).getParkingSpot(spot.getSpotNumber()+1);

        assertTrue( spot.getVehicles().get(0).getNumber() == "KA12MH4567");
        assertTrue(newSpot.getSpotType() == ParkingType.RESERVED);
    }

    @Test
    public void removeVehicleWhichIsNotRoyal() throws ParkingException {
        MultiLevelParking parking = new MultiLevelParking();
        parking.intializeMultiParking(1);
        IVehicle car = new Car(ParkingType.CAR, "KA12MH4567");

        ParkingStrategy strategy = new ParkingStrategy();

        ParkingSpot spot = strategy.assignVehicle(parking, false, car).getValue();
        VehicleInfo info = new VehicleInfo();
        info.setParkingLevel(0);
        info.setSpot(spot);
        strategy.removeVehicle(parking, info, "KA12MH4567");

        ParkingSpot newSpot = parking.getParkingAreas().get(0).getParkingSpot(spot.getSpotNumber());

        System.out.println(newSpot.toString());
        assertTrue(newSpot.getVehicles().size() == 0);
    }

    @Test
    public void removeRoyalVehicle() throws ParkingException {
        MultiLevelParking parking = new MultiLevelParking();
        parking.intializeMultiParking(1);
        IVehicle royalVehicle = new RoyalVehicle(ParkingType.ROYAL, "KA12MH4567");

        ParkingStrategy strategy = new ParkingStrategy();

        ParkingSpot spot = strategy.assignVehicle(parking, false, royalVehicle).getValue();
        VehicleInfo info = new VehicleInfo();
        info.setParkingLevel(0);
        info.setSpot(spot);
        strategy.removeVehicle(parking, info, "KA12MH4567");

        ParkingSpot newSpot = parking.getParkingAreas().get(0).getParkingSpot(spot.getSpotNumber()+1);

        System.out.println(newSpot.toString());
        assertTrue(newSpot.getSpotType() == null);
    }
}
