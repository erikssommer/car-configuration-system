package org.semesteroppgave.carmodel;

import org.semesteroppgave.carcomponents.*;
import org.semesteroppgave.carcustomization.Gps;
import org.semesteroppgave.carcustomization.Sunroof;
import org.semesteroppgave.carcustomization.Towbar;

public class Hybrid extends Car {
    private Battery battery;
    private FuelContainer fuelContainer;
    private String model;
    private double modelPrice;

    public Hybrid(Motor motor, Rim rim, SeatCover seatcover, Spoiler spoiler, Tires tires, Gps gps, Sunroof sunroof, Towbar towbar, Battery battery, FuelContainer fuelContainer) {
        super(motor, rim, seatcover, spoiler, tires, gps, sunroof, towbar);
        if (fuelContainer == null || battery == null) throw new NullPointerException("Du har glemt å opprette et batteri eller en tank");
        this.battery = battery;
        this.fuelContainer = fuelContainer;
        this.model = "Hybrid";
        this.modelPrice = 850_000;
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

    public double getModelPrice(){
        return this.modelPrice;
    }

    @Override
    public double getPrice() {
        double price = getModelPrice() + getMotor().getPrice() + getRim().getPrice() + getSeatCover().getPrice() +
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
        setTotalPrice(price);
        return price;
    }

    @Override
    public String toString(){
        String message =
                "Bilmodell: "+getModel()+"\nModellpris: "+getModelPrice()+"\n\n"+
                super.toString()+
                "Batteri : "+getBattery().getVersion()+"\npris: "+ getBattery().getPrice()+"\nBeskrivelse: "+getBattery().getDescription()+"\n\n"+
                "Tank : "+getFuelContainer().getVersion()+"\npris: "+ getFuelContainer().getPrice()+"\nBeskrivelse: "+getFuelContainer().getDescription()+"\n\n"+
                "Tilpasninger som er valgt for konfigurasjonen: \n\n";

        if (getGps() != null){
            message += getGps().getCustomProperty()+"\npris: "+getGps().getPrice()+"\n\n";
        }
        if (getSunroof() != null){
            message += getSunroof().getCustomProperty()+"\npris: "+getSunroof().getPrice()+"\n\n";
        }
        if (getTowbar() != null){
            message += getTowbar().getCustomProperty()+"\npris: "+getTowbar().getPrice()+"\n\n";
        }
        if (getGps() == null && getSunroof() == null && getTowbar() == null){
            message += "Denne komfigurasjonen har ingen tilpasninger\n\n";
        }

        message += "Totalprisen på produktet er: " + getTotalPrice();
        return message;
    }
}
