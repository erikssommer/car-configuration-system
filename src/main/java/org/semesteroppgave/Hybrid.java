package org.semesteroppgave;

public class Hybrid extends Car {
    private Battery battery;
    private FuelContainer fuelContainer;

    public Hybrid(Motor motor, Rim rim, Seatcover seatcover, Spoiler spoiler, Drivetype drivetype, Tires tires, Battery battery, FuelContainer fuelContainer) {
        super(motor, rim, seatcover, spoiler, drivetype, tires);
        this.battery = battery;
        this.fuelContainer = fuelContainer;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public FuelContainer getFuelContainer() {
        return fuelContainer;
    }

    public void setFuelContainer(FuelContainer fuelContainer) {
        this.fuelContainer = fuelContainer;
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
