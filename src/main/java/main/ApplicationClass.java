package main;

import domain.*;

import java.util.Scanner;

public class ApplicationClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleService vehicleService = new VehicleService();

        int continueOrNot = 1;
        while (continueOrNot != 0) {
            System.out.println("----------------------");
            System.out.println("Please select an option");
            System.out.println("1. - Search by Automaker");
            System.out.println("2. - Search by Car Model");
            System.out.println("3. - Generate Vehicles report");
            System.out.println("4. - Delete a Vehicle");
            System.out.println("5. - Update a Vehicle");
            int selectedOption = scanner.nextInt();

            switch (selectedOption) {
                case 1 -> {
                    VehicleService.searchByAutomaker();

                }
                case 2 -> {
                    vehicleService.searchByModel();
                }

                case 3 -> {
                    vehicleService.generateReport();
                }
                case 4 -> {
                    vehicleService.deleteVehicleByModel();
                }case 5 -> {

                    vehicleService.updateVehicle();
                }
                default -> System.out.println("Invalid input");
            }
            System.out.println("----------------------------");
            System.out.println("Do you wish to continue?");
            System.out.println("0. No");
            System.out.println("1. Yes");
            continueOrNot = scanner.nextInt();
        }
        System.out.println("Program Terminated");
    }
}
