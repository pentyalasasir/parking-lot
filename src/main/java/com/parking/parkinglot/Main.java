package com.parking.parkinglot;

import com.parking.parkinglot.exception.ParkingException;
import com.parking.parkinglot.model.*;
import com.parking.parkinglot.parking.MultiLevelParking;
import com.parking.parkinglot.strategy.ParkingStrategy;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Main main = new Main();
        try {
            main.start(scanner);
        } catch (Exception e) {
            System.out.println("Invalid input" + "\n");
        }


    }

    public void start(Scanner scanner) throws Exception {
        ParkingStrategy strategy = new ParkingStrategy();
        Map<String, VehicleInfo> storeInfo = new HashMap<>();

        System.out.println("Enter levels: ");
        int levels = scanner.nextInt();


        MultiLevelParking parking = new MultiLevelParking();
        parking.intializeMultiParking(levels);

        while (true) {

            System.out.println("1. Incoming Vehicle");
            System.out.println("2. Outgoing Vehicle");
            System.out.println("3. Parking Info");
            System.out.println("Enter your choice: ");
            int info = scanner.nextInt();

            if (info == 1) {
                System.out.println("Enter vehicle number: ");
                String vehicleNumber = scanner.next();

                System.out.println("Please select vehicle Type: ");
                System.out.println("1. CAR");
                System.out.println("2. BIKE");
                System.out.println("3. VIP");
                System.out.println("Enter your Choice: ");
                int parkingType = scanner.nextInt();

                ParkingType type;
                IVehicle vehicle;
                switch (parkingType) {
                    case 1:
                        type = ParkingType.CAR;
                        vehicle = new Car(type, vehicleNumber);
                        break;
                    case 2:
                        type = ParkingType.BIKE;
                        vehicle = new Bike(type, vehicleNumber);
                        break;
                    case 3:
                        type = ParkingType.ROYAL;
                        vehicle = new RoyalVehicle(type, vehicleNumber);
                        break;
                    default:
                        type = null;
                        vehicle = null;
                }

                System.out.println("Is an Elderly Person(true/false): ");
                boolean isElderly = scanner.nextBoolean();

                Pair<Integer, ParkingSpot> pair = null;
                try {
                    pair = strategy.assignVehicle(parking, isElderly, vehicle);
                    VehicleInfo vehicleInfo = new VehicleInfo();
                    vehicleInfo.setParkingLevel(pair.getKey());
                    vehicleInfo.setSpot(pair.getValue());
                    storeInfo.put(vehicle.getNumber(), vehicleInfo);
                    System.out.println(vehicleInfo.toString() + "\n");
                } catch (ParkingException e) {
                    System.out.println("No Parking available" + "\n");
                }

            } else if (info == 2) {
                System.out.println("Enter outgoing vehicle number: ");
                String outgoingVehicleNumber = scanner.next();

                VehicleInfo outgoing = storeInfo.get(outgoingVehicleNumber);
                try {
                    ParkingSpot spot = strategy.removeVehicle(parking, outgoing, outgoingVehicleNumber);
                    storeInfo.remove(outgoingVehicleNumber);
                    System.out.println("Removed Vehicle at Parking Level: " + outgoing.getParkingLevel() + " at Spot Number: " + spot.getSpotNumber() + "\n");
                } catch (ParkingException e) {
                    System.out.println("No Vehicle found with the given number" + "\n");
                }


            } else if (info == 3) {
                System.out.println(strategy.getParkingInfo(parking) + "\n");
            } else {
                System.out.println("Wrong input entered");
            }

        }


    }
}
