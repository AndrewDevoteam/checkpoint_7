package domain;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class VehicleService {

    public static final Scanner SCANNER = new Scanner(System.in);
    private AutomakerService automakerService = new AutomakerService();

    private final VehicleRepository vehicleRepository = new VehicleRepository();

    public static void searchByAutomaker(){
        System.out.println("Type the name or empty to list all");
        String name = SCANNER.nextLine();
        List<AutoMaker> producers = VehicleRepository.findByAutomaker(name);
        producers.forEach(a -> System.out.printf("[%d] - %s%n", a.getId(), a.getName()));
    }


    public void searchByModel() {
        System.out.println("Please type in the model name");
        String name = SCANNER.nextLine();
        Optional<Vehicle> vehicle = VehicleRepository.findByModel(name);
        System.out.printf("[%d] - %s%n", vehicle.get().getId(), vehicle.get().getModel());
    }

    public void generateReport(){
        List<Vehicle> vehicles = VehicleRepository.findAll();
        vehicles.forEach(v -> System.out.printf("[%d] - %s, %s, %s, %s, %s %n", v.getId(), v.getModel(),v.getYear(), v.getColor(), v.getAutoMaker().getName(), v.getVehicleType()));
    }

    public void deleteVehicleByModel() {
        System.out.println("Please type in the vehicle id");
        Integer id = SCANNER.nextInt();
        VehicleRepository.delete(id);
        System.out.println("Vehicle was successfully deleted");
    }

    public void updateVehicle(){
        System.out.println("Please type in the name of the automaker");
        String autoMakerName = SCANNER.nextLine();
        List<AutoMaker> automakers = AutomakerRepository.findByName(autoMakerName);
        if(automakers.isEmpty()){
            AutoMaker autoMaker = AutoMaker
                    .builder()
                    .name(autoMakerName)
                    .build();
            AutomakerRepository.save(autoMaker);
            System.out.println("Automaker successfully added to database");
        }
        System.out.println("Please type in the name of the vehicle you want to update");
        String model = SCANNER.nextLine();
        Optional<Vehicle> optionalVehicle = VehicleRepository.findByModel(model);

        Vehicle vehicleFromDb = optionalVehicle.get();
        System.out.println("Vehicle Found " + vehicleFromDb);
        System.out.println("Type the new name or enter to keep the same name");
        String name = SCANNER.nextLine();
        System.out.println("Type the new color or enter to keep the same name");
        String color = SCANNER.nextLine();
        System.out.println("Type the new Year of production or enter to keep the same name");
        String year = SCANNER.nextLine();
        System.out.println("Type the new automaker or enter to keep the same name");
        String automaker = SCANNER.nextLine();
        System.out.println("Type the new vehicle_type or enter to keep the same name");
        String vehicleType = SCANNER.nextLine();
        System.out.println("Type the updated date created or enter to keep the same name");
        String dateCreated = SCANNER.nextLine();


        AutoMaker autoMaker = AutoMaker
                .builder()
                .name(automaker)
                .build();
        Vehicle vehicle = createVehicle(
                vehicleFromDb.getId(),
                name,
                color,
                year,
                autoMaker,
                VehicleTypeEnum.valueOf(vehicleType));
        VehicleRepository.replaceVehicle(vehicle);
    }

    public Vehicle createVehicle(long id, String name,String color, String year, AutoMaker automaker, VehicleTypeEnum vehicleTypeEnum) {
        return switch (vehicleTypeEnum) {
            case CAR -> VehicleTypeEnum.CAR.buildNewVehicle(id, name, color, year, automaker, vehicleTypeEnum);
            case MOTORCYCLE -> VehicleTypeEnum.MOTORCYCLE.buildNewVehicle(id,name, color, year, automaker, vehicleTypeEnum);
            case VAN -> VehicleTypeEnum.VAN.buildNewVehicle(id,name, color, year, automaker, vehicleTypeEnum);
            case TRUCK -> VehicleTypeEnum.TRUCK.buildNewVehicle(id,name, color, year, automaker, vehicleTypeEnum);
            case PICKUP -> VehicleTypeEnum.PICKUP.buildNewVehicle(id,name, color, year, automaker, vehicleTypeEnum);
            case OTHERS -> VehicleTypeEnum.OTHERS.buildNewVehicle(id,name, color, year, automaker, vehicleTypeEnum);
        };
    }
}
