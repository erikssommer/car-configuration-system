package org.semesteroppgave;

public class Electric extends Car {

    private Battery battery;

    public Electric(Motor motor, Rim rim, SeatCover seatcover, Spoiler spoiler, Tires tires, Battery battery) {
        super(motor, rim, seatcover, spoiler, tires);
        this.battery = battery;
    }

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
