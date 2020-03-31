package org.semesteroppgave.carmodel;

import org.semesteroppgave.carcomponents.*;
import org.semesteroppgave.carcustomization.Gps;
import org.semesteroppgave.carcustomization.Sunroof;
import org.semesteroppgave.carcustomization.Towbar;

public class Hybrid extends Car {
    private Battery battery;
    private FuelContainer fuelContainer;
    private String model;

    public Hybrid(Motor motor, Rim rim, SeatCover seatcover, Spoiler spoiler, Tires tires, Gps gps, Sunroof sunroof, Towbar towbar, Battery battery, FuelContainer fuelContainer) {
        super(motor, rim, seatcover, spoiler, tires, gps, sunroof, towbar);
        this.battery = battery;
        this.fuelContainer = fuelContainer;
        this.model = "Hybrid";
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

    public String getModel(){
        return this.model;
    }

    @Override
    public double getPrice() {
        double price = getMotor().getPrice() + getRim().getPrice() + getSeatCover().getPrice() +
                getSpoiler().getPrice() + getTires().getPrice() + getBattery().getPrice() + getFuelContainer().getPrice();
        if (getGps() != null){
            price += getGps().getPrice();
        }
        if (getSunroof() != null){
            price += getSunroof().getPrice();
        }
        if (getTowbar() != null){
            price += getTowbar().getPrice();
        }
        return price;
    }
}
