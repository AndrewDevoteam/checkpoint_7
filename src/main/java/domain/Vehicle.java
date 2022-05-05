package domain;

import interfaces.GetVehicleType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;


public abstract class Vehicle implements GetVehicleType {

    private final long id;
    private String model;
    private String color;
    private String year;
    private AutoMaker autoMaker;
    private VehicleTypeEnum vehicleTypeEnum;
    public final LocalDateTime createdAt= createdAt();

    private final LocalDateTime createdAt() {
        long l = ThreadLocalRandom.current().nextLong(0, 100_000);
        LocalDateTime randomldt = LocalDateTime.now().plusDays(l).plusMinutes(l);
        return randomldt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id == vehicle.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public Vehicle(long id, String model, String color, String year, AutoMaker autoMaker, VehicleTypeEnum vehicleTypeEnum) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.year = year;
        this.autoMaker = autoMaker;
        this.vehicleTypeEnum = vehicleTypeEnum;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", year='" + year + '\'' +
                ", autoMaker=" + autoMaker +
                ", vehicleTypeEnum=" + vehicleTypeEnum +
                ", createdAt=" + createdAt +
                '}';
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public AutoMaker getAutoMaker() {
        return autoMaker;
    }

    public void setAutoMaker(AutoMaker autoMaker) {
        this.autoMaker = autoMaker;
    }

    public VehicleTypeEnum getVehicleTypeEnum() {
        return vehicleTypeEnum;
    }

    public void setVehicleTypeEnum(VehicleTypeEnum vehicleTypeEnum) {
        this.vehicleTypeEnum = vehicleTypeEnum;
    }


};

