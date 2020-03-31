package org.semesteroppgave.carmodel;

import org.semesteroppgave.carcomponents.*;

public class Electric extends Car {

    private Battery battery;
    private String model;

    public Electric(Motor motor, Rim rim, SeatCover seatcover, Spoiler spoiler, Tires tires, Battery battery) {
        super(motor, rim, seatcover, spoiler, tires);
        this.battery = battery;
        this.model = "Elektrisk";
    }

    public Electric(){}

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public String getModel(){
        return this.model;
    }

    @Override
    public double getPrice() {
        return getMotor().getPrice() + getRim().getPrice() + getSeatCover().getPrice() +
                getSpoiler().getPrice() + getTires().getPrice(); // + getBattery().getPrice();
    }
}
