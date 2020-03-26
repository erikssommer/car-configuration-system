package org.semesteroppgave;

public class Diesel extends Car {

    private FuelContainer fuelContainer;
    private Gearbox gearbox;

    public Diesel(Motor motor, Rim rim, Seatcover seatcover, Spoiler spoiler, Drivetype drivetype, Tires tires, FuelContainer fuelContainer, Gearbox gearbox) {
        super(motor, rim, seatcover, spoiler, drivetype, tires);
        this.fuelContainer = fuelContainer;
        this.gearbox = gearbox;
    }

    public FuelContainer getFuelContainer() {
        return fuelContainer;
    }

    public void setFuelContainer(FuelContainer fuelContainer) {
        this.fuelContainer = fuelContainer;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
