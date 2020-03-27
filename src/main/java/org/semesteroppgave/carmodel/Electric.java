package org.semesteroppgave.carmodel;

import org.semesteroppgave.carcomponents.*;

public class Electric extends Car {

    private Battery battery;

    public Electric(Motor motor, Rim rim, SeatCover seatcover, Spoiler spoiler, Tires tires, Battery battery) {
        super(motor, rim, seatcover, spoiler, tires);
        this.battery = battery;
    }

    public Electric(){}

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
