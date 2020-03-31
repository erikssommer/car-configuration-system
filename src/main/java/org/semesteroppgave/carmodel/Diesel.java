package org.semesteroppgave.carmodel;

import org.semesteroppgave.carcomponents.*;

public class Diesel extends Car {

    private FuelContainer fuelContainer;
    private Gearbox gearbox;
    private String model;

    public Diesel(Motor motor, Rim rim, SeatCover seatcover, Spoiler spoiler, Tires tires, FuelContainer fuelContainer, Gearbox gearbox) {
        super(motor, rim, seatcover, spoiler, tires);
        this.fuelContainer = fuelContainer;
        this.gearbox = gearbox;
        this.model = "Diesel";
    }

    public Diesel(){}

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

    public String getModel(){
        return this.model;
    }

    @Override
    public double getPrice() {
        return getMotor().getPrice() + getRim().getPrice() + getSeatCover().getPrice() +
                getSpoiler().getPrice() + getTires().getPrice(); // + getFuelContainer().getPrice() + getGearbox().getPrice();
    }
}
