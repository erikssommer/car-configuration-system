package org.semesteroppgave.carmodel;

import org.semesteroppgave.carcomponents.*;
import org.semesteroppgave.carcustomization.Gps;
import org.semesteroppgave.carcustomization.Sunroof;
import org.semesteroppgave.carcustomization.Towbar;

public class Diesel extends Car {

    private FuelContainer fuelContainer;
    private Gearbox gearbox;
    private String model;
    private double modelPrice;

    public Diesel(Motor motor, Rim rim, SeatCover seatcover, Spoiler spoiler, Tires tires, Gps gps, Sunroof sunroof, Towbar towbar, FuelContainer fuelContainer, Gearbox gearbox) {
        super(motor, rim, seatcover, spoiler, tires, gps, sunroof, towbar);
        if (fuelContainer == null || gearbox == null) throw new NullPointerException("Du har glemt å opprette en tank eller en girboks");
        this.fuelContainer = fuelContainer;
        this.gearbox = gearbox;
        this.model = "Diesel";
        this.modelPrice = 400_000;
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
    public double getModelPrice(){
        return this.modelPrice;
    }

    @Override
    public double getPrice() {
        double price = getModelPrice() + getMotor().getPrice() + getRim().getPrice() + getSeatCover().getPrice() +
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
        setTotalPrice(price);
        return price;
    }

    @Override
    public String toString(){
        String message =
                "Bilmodell: "+getModel()+"\nModellpris: "+getModelPrice()+"\n\n"+
                super.toString()+
                "Tank: "+getFuelContainer().getVersion()+"\nPris: "+ getFuelContainer().getPrice()+"\nBeskrivelse: "+getFuelContainer().getDescription()+"\n\n"+
                "Girboks: "+getGearbox().getVersion()+"\nPris: "+ getGearbox().getPrice()+"\nBeskrivelse: "+getGearbox().getDescription()+"\n\n"+
                "Tilpasninger som er valgt for konfigurasjonen: \n\n";
        if (getGps() != null){
            message += getGps().getCustomProperty()+"\nPris: "+getGps().getPrice()+"\n\n";
        }
        if (getSunroof() != null){
            message += getSunroof().getCustomProperty()+"\nPris: "+getSunroof().getPrice()+"\n\n";
        }
        if (getTowbar() != null){
            message += getTowbar().getCustomProperty()+"\nPris: "+getTowbar().getPrice()+"\n\n";
        }
        if (getGps() == null && getSunroof() == null && getTowbar() == null){
            message += "Denne komfigurasjonen har ingen tilpasninger\n\n";
        }

        message += "Totalprisen på produktet er: " + getTotalPrice();
        return message;
    }
}
