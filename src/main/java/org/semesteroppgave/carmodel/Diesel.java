package org.semesteroppgave.carmodel;

import org.semesteroppgave.carcomponents.*;
import org.semesteroppgave.carcustomization.Gps;
import org.semesteroppgave.carcustomization.Sunroof;
import org.semesteroppgave.carcustomization.Towbar;

public class Diesel extends Car {

    private FuelContainer fuelContainer;
    private Gearbox gearbox;
    private String model;

    public Diesel(Motor motor, Rim rim, SeatCover seatcover, Spoiler spoiler, Tires tires, Gps gps, Sunroof sunroof, Towbar towbar, FuelContainer fuelContainer, Gearbox gearbox) {
        super(motor, rim, seatcover, spoiler, tires, gps, sunroof, towbar);
        this.fuelContainer = fuelContainer;
        this.gearbox = gearbox;
        this.model = "Diesel";
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

    public String getModel(){
        return this.model;
    }

    @Override
    public double getPrice() {
        double price = getMotor().getPrice() + getRim().getPrice() + getSeatCover().getPrice() +
                getSpoiler().getPrice() + getTires().getPrice() + getFuelContainer().getPrice() + getGearbox().getPrice();
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
