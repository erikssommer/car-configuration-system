package org.semesteroppgave.carmodel;

import org.semesteroppgave.carcomponents.*;
import org.semesteroppgave.carcustomization.Gps;
import org.semesteroppgave.carcustomization.Sunroof;
import org.semesteroppgave.carcustomization.Towbar;

import java.text.DecimalFormat;

public class Hybrid extends Car {
    private Battery battery;
    private FuelContainer fuelContainer;
    private final String model;
    private final double modelPrice;

    public Hybrid(Motor motor, Rim rim, SeatCover seatcover, Spoiler spoiler, Tires tires, Gps gps, Sunroof sunroof, Towbar towbar, Battery battery, FuelContainer fuelContainer) {
        super(motor, rim, seatcover, spoiler, tires, gps, sunroof, towbar);
        if (fuelContainer == null) throw new NullPointerException("Du har glemt å velge en tank");
        if (battery == null) throw new NullPointerException("Du har glemt å velge et batteri");
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

    public String toFile(){

        return getModel() +";"+getModelPrice()+";"+ super.toFile()+";"+ getBattery().toFile()+";"+
                getFuelContainer().toFile()+";;;;"+super.customToFile(null)+getPrice();

    }

    @Override
    public double getPrice() {

        return super.getPrice() + getModelPrice() + getBattery().getPrice() + getFuelContainer().getPrice();

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Hybrid) {
            Hybrid product = (Hybrid) obj;
            return super.equals(product) && product.getBattery().equals(battery)
                    && product.getFuelContainer().equals(fuelContainer) && product.getPrice() == getPrice();
        }
        return false;
    }

    @Override
    public String toString(){

        DecimalFormat df = new DecimalFormat("###,###,###.###");
        String message = "Bilmodell: "+getModel()+"\nModellpris: "+df.format(getModelPrice())+"kr\n\n"+ super.toString()+
        "Batteri : "+getBattery().getVersion()+"\nPris: "+ df.format(getBattery().getPrice())+"kr\nBeskrivelse: "+getBattery().getDescription()+"\n\n"+
        "Tank : "+getFuelContainer().getVersion()+"\nPris: "+ df.format(getFuelContainer().getPrice())+"kr\nBeskrivelse: "+getFuelContainer().getDescription()+"\n\n"+
        "Tilpasninger som er valgt for konfigurasjonen: \n\n";

        message += super.testCustom(df, null);

        message += "Totalprisen på produktet er: " + df.format(getPrice()) + "kr";
        return message;


    }
}
