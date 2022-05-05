package domain;

public class Truck extends Vehicle{
    public Truck(long id, String model, String color, String year, AutoMaker autoMaker, VehicleTypeEnum vehicleTypeEnum) {
        super(id, model, color, year, autoMaker, vehicleTypeEnum);
    }

    @Override
    public VehicleTypeEnum getVehicleType() {
        return VehicleTypeEnum.TRUCK;
    }
}
