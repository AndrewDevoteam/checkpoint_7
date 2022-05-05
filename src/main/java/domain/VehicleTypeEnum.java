package domain;

public enum VehicleTypeEnum {

    CAR {
        @Override
        public Vehicle buildNewVehicle(long id, String name,String color, String year, AutoMaker automaker, VehicleTypeEnum vehicleTypeEnum) {
            return new Car( id, name, color, year, automaker, vehicleTypeEnum);

        }
    },
    MOTORCYCLE {
        @Override
        public Vehicle buildNewVehicle(long id, String name,String color, String year, AutoMaker automaker, VehicleTypeEnum vehicleTypeEnum) {
            return new Motorcycle(id, name, color, year, automaker, vehicleTypeEnum);

        }
    },
    VAN {
        @Override
        public Vehicle buildNewVehicle(long id, String name,String color, String year, AutoMaker automaker, VehicleTypeEnum vehicleTypeEnum) {
            return new Van(id, name, color, year, automaker, vehicleTypeEnum);
        }
    },
    TRUCK {
        @Override
        public Vehicle buildNewVehicle(long id, String name,String color, String year, AutoMaker automaker, VehicleTypeEnum vehicleTypeEnum) {
            return new Truck(id, name, color, year, automaker, vehicleTypeEnum);

        }
    },
    PICKUP {
        @Override
        public Vehicle buildNewVehicle(long id, String name,String color, String year, AutoMaker automaker, VehicleTypeEnum vehicleTypeEnum) {
            return new PickUp(id, name, color, year, automaker, vehicleTypeEnum);

        }
    },
    OTHERS {
        @Override
        public Vehicle buildNewVehicle(long id,  String name,String color, String year, AutoMaker automaker, VehicleTypeEnum vehicleTypeEnum) {
            return new Others(id, name, color, year, automaker, vehicleTypeEnum);
        }
    };

    public Vehicle buildNewVehicle(long id, String name,String color, String year, AutoMaker automaker, VehicleTypeEnum vehicleTypeEnum) {
        return null;
    }
}
