package org.semesteroppgave.carmodel;

import org.semesteroppgave.carcomponents.*;
import org.semesteroppgave.carcustomization.Gps;
import org.semesteroppgave.carcustomization.Sunroof;
import org.semesteroppgave.carcustomization.Towbar;

import java.text.DecimalFormat;

public class Diesel extends Car {

    private FuelContainer fuelContainer;
    private Gearbox gearbox;
    private String model;
    private double modelPrice;

    public Diesel(Motor motor, Rim rim, SeatCover seatcover, Spoiler spoiler, Tires tires, Gps gps, Sunroof sunroof, Towbar towbar, FuelContainer fuelContainer, Gearbox gearbox) {
        super(motor, rim, seatcover, spoiler, tires, gps, sunroof, towbar);
        if (fuelContainer == null) throw new NullPointerException("Du har glemt å velge en tank");
        if (gearbox == null) throw new NullPointerException("Du har glemt å velge en girboks");
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
        return super.getPrice() + getModelPrice() + getFuelContainer().getPrice() + getGearbox().getPrice();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Diesel) {
            Diesel product = (Diesel) obj;
            return super.equals(product) && product.getFuelContainer().equals(fuelContainer)
                    && product.getGearbox().equals(gearbox) && product.getPrice() == getPrice();
        }
        return false;
    }

    @Override
    public String toString(){

         DecimalFormat df = new DecimalFormat("###,###,###.###");
         String message = "Bilmodell: "+getModel()+"\nModellpris: "+df.format(getModelPrice())+"kr\n\n"+ super.toString()+
                 "Tank: "+getFuelContainer().getVersion()+"\nPris: "+ df.format(getFuelContainer().getPrice())+"kr\nBeskrivelse: "+getFuelContainer().getDescription()+"\n\n"+
                 "Girboks: "+getGearbox().getVersion()+"\nPris: "+ df.format(getGearbox().getPrice())+"kr\nBeskrivelse: "+getGearbox().getDescription()+"\n\n"+
                 "Tilpasninger som er valgt for konfigurasjonen: \n\n";

         message += super.testCustom(df, null);

         message += "Totalprisen på produktet er: " + df.format(getPrice()) + "kr";
         return message;

    }
}
