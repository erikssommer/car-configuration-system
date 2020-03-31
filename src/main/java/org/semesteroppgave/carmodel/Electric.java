package org.semesteroppgave.carmodel;

import org.semesteroppgave.carcustomization.Autopilot;
import org.semesteroppgave.carcomponents.*;
import org.semesteroppgave.carcustomization.Gps;
import org.semesteroppgave.carcustomization.Sunroof;
import org.semesteroppgave.carcustomization.Towbar;

public class Electric extends Car {

    private Battery battery;
    private String model;
    private Autopilot autopilot;

    public Electric(Motor motor, Rim rim, SeatCover seatcover, Spoiler spoiler, Tires tires, Gps gps, Sunroof sunroof, Towbar towbar, Battery battery, Autopilot autopilot) {
        super(motor, rim, seatcover, spoiler, tires, gps, sunroof, towbar);
        this.battery = battery;
        this.model = "Elektrisk";
        this.autopilot = autopilot;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public String getModel(){
        return this.model;
    }

    public Autopilot getAutopilot() {
        return autopilot;
    }

    public void setAutopilot(Autopilot autopilot) {
        this.autopilot = autopilot;
    }

    @Override
    public double getPrice() {
        double price = getMotor().getPrice() + getRim().getPrice() + getSeatCover().getPrice() +
                getSpoiler().getPrice() + getTires().getPrice() + getBattery().getPrice();
        if (getAutopilot() != null){
            price += getAutopilot().getPrice();
        }
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
