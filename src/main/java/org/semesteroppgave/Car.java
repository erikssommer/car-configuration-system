package org.semesteroppgave;

import java.io.Serializable;

public abstract class Car implements Serializable {
    private Motor motor;
    private Rim rim;
    private Seatcover seatcover;
    private Spoiler spoiler;
    private Drivetype drivetype;
    private Tires tires;

    public Car(Motor motor, Rim rim, Seatcover seatcover, Spoiler spoiler, Drivetype drivetype, Tires tires) {
        this.motor = motor;
        this.rim = rim;
        this.seatcover = seatcover;
        this.spoiler = spoiler;
        this.drivetype = drivetype;
        this.tires = tires;
    }

    public abstract int getPrice();

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Rim getRim() {
        return rim;
    }

    public void setRim(Rim rim) {
        this.rim = rim;
    }

    public Seatcover getSeatcover() {
        return seatcover;
    }

    public void setSeatcover(Seatcover seatcover) {
        this.seatcover = seatcover;
    }

    public Spoiler getSpoiler() {
        return spoiler;
    }

    public void setSpoiler(Spoiler spoiler) {
        this.spoiler = spoiler;
    }

    public Drivetype getDrivetype() {
        return drivetype;
    }

    public void setDrivetype(Drivetype drivetype) {
        this.drivetype = drivetype;
    }

    public Tires getTires() {
        return tires;
    }

    public void setTires(Tires tires) {
        this.tires = tires;
    }
}
