package org.semesteroppgave.carmodel;

import org.semesteroppgave.carcustomization.Autopilot;
import org.semesteroppgave.carcomponents.*;
import org.semesteroppgave.carcustomization.Gps;
import org.semesteroppgave.carcustomization.Sunroof;
import org.semesteroppgave.carcustomization.Towbar;

import java.text.DecimalFormat;

public class Electric extends Car {

    private Battery battery;
    private Autopilot autopilot;
    private final String model;
    private final double modelPrice;

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
        double price = super.getPrice()+ getModelPrice() + getBattery().getPrice();

        if (getAutopilot() != null){
            price += getAutopilot().getPrice();
        }

        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Electric) {
            Electric product = (Electric) obj;
            return super.equals(product) && product.getBattery().equals(battery)
                    && ((product.getAutopilot() == null && autopilot == null) || product.getAutopilot().equals(autopilot));
        }
        return false;
    }

    @Override
    public String toString(){

        DecimalFormat df = new DecimalFormat("###,###,###.###");
        String message = "Bilmodell: "+getModel()+"\nModellpris: "+df.format(getModelPrice())+"kr\n\n"+ super.toString()+
        "Batteri : "+getBattery().getVersion()+"\nPris: "+ df.format(getBattery().getPrice())+"kr\nBeskrivelse: " + getBattery().getDescription()+"\n\n"+
        "Tilpasninger som er valgt for konfigurasjonen: \n\n";

        message += super.testCustom(df, getAutopilot());

        message += "Totalprisen på produktet er: " + df.format(getPrice()) + "kr";
        return message;



    }
}
