package org.semesteroppgave.carmodel;

import org.semesteroppgave.carcomponents.*;

import java.io.Serializable;

public abstract class Car implements Serializable {
    private Motor motor;
    private Rim rim;
    private SeatCover seatcover;
    private Spoiler spoiler;
    private Tires tires;

    public Car(Motor motor, Rim rim, SeatCover seatcover, Spoiler spoiler, Tires tires) {
        this.motor = motor;
        this.rim = rim;
        this.seatcover = seatcover;
        this.spoiler = spoiler;
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

    public SeatCover getSeatCover() {
        return seatcover;
    }

    public void setSeatCover(SeatCover seatcover) {
        this.seatcover = seatcover;
    }

    public Spoiler getSpoiler() {
        return spoiler;
    }

    public void setSpoiler(Spoiler spoiler) {
        this.spoiler = spoiler;
    }

    public Tires getTires() {
        return tires;
    }

    public void setTires(Tires tires) {
        this.tires = tires;
    }
}
