package domain;

public class Others extends Vehicle{
    public Others(long id, String model, String color, String year, AutoMaker autoMaker, VehicleTypeEnum vehicleTypeEnum) {
        super(id, model, color, year, autoMaker, vehicleTypeEnum);
    }

    @Override
    public VehicleTypeEnum getVehicleType() {
        return VehicleTypeEnum.OTHERS;
    }
}
