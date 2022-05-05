package domain;

public class Motorcycle extends Vehicle{
    public Motorcycle(long id, String model, String color, String year, AutoMaker autoMaker, VehicleTypeEnum vehicleTypeEnum) {
        super(id, model, color, year, autoMaker, vehicleTypeEnum);
    }

    @Override
    public VehicleTypeEnum getVehicleType() {
        return VehicleTypeEnum.MOTORCYCLE;
    }
}
