package domain;

public class PickUp extends Vehicle{
    public PickUp(long id, String model, String color, String year, AutoMaker autoMaker, VehicleTypeEnum vehicleTypeEnum) {
        super(id, model, color, year, autoMaker, vehicleTypeEnum);
    }

    @Override
    public VehicleTypeEnum getVehicleType() {
        return VehicleTypeEnum.PICKUP;
    }
}
