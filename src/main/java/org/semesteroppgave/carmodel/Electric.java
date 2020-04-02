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
    private double modelPrice;

    public Electric(Motor motor, Rim rim, SeatCover seatcover, Spoiler spoiler, Tires tires, Gps gps, Sunroof sunroof, Towbar towbar, Battery battery, Autopilot autopilot) {
        super(motor, rim, seatcover, spoiler, tires, gps, sunroof, towbar);
        if (battery == null) throw new NullPointerException("Du har glemt å velge et batteri");
        this.battery = battery;
        this.autopilot = autopilot;
        this.model = "Elektrisk";
        this.modelPrice = 1_200_000;
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

    public double getModelPrice(){
        return this.modelPrice;
    }

    @Override
    public double getPrice() {
        double price = getModelPrice() + getMotor().getPrice() + getRim().getPrice() + getSeatCover().getPrice() +
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
        setTotalPrice(price);
        return price;
    }

    @Override
    public String toString(){
        String message =
                "Bilmodell: "+getModel()+"\nModellpris: "+getModelPrice()+"\n\n"+
                super.toString()+
                "Batteri : "+getBattery().getVersion()+"\nPris: "+ getBattery().getPrice()+"\nBeskrivelse: "+getBattery().getDescription()+"\n\n"+
                "Tilpasninger som er valgt for konfigurasjonen: \n\n";
        if (getAutopilot() != null){
            message += getAutopilot().getCustomProperty()+"\nPris: "+getAutopilot().getPrice()+"\n\n";
        }
        if (getGps() != null){
            message += getGps().getCustomProperty()+"\nPris: "+getGps().getPrice()+"\n\n";
        }
        if (getSunroof() != null){
            message += getSunroof().getCustomProperty()+"\nPris: "+getSunroof().getPrice()+"\n\n";
        }
        if (getTowbar() != null){
            message += getTowbar().getCustomProperty()+"\nPris: "+getTowbar().getPrice()+"\n\n";
        }
        if (getAutopilot() == null && getGps() == null && getSunroof() == null && getTowbar() == null){
            message += "Denne komfigurasjonen har ingen tilpasninger\n\n";
        }

        message += "Totalprisen på produktet er: " + getTotalPrice();
        return message;
    }
}
