package org.semesteroppgave;

public class Electric extends Car {

    private Battery battery;

    public Electric(Motor motor, Rim rim, Seatcover seatcover, Spoiler spoiler, Drivetype drivetype, Tires tires, Battery battery) {
        super(motor, rim, seatcover, spoiler, drivetype, tires);
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
