package org.semesteroppgave.models.data.carmodel;

import org.semesteroppgave.models.data.carcomponents.*;
import org.semesteroppgave.models.data.carcustomization.*;
import org.semesteroppgave.models.exceptions.EmptyComponentException;

import java.text.DecimalFormat;

public class Electric extends Car {

    private final Battery battery;
    private final Autopilot autopilot;

    public Electric(Builder builder) {
        super(builder);
        this.battery = builder.battery;
        this.autopilot = builder.autopilot;
    }

    public static class Builder extends Car.Builder<Builder> {
        private Battery battery;
        private Autopilot autopilot;

        public Builder(String model, double modelPrice) {
            super(model, modelPrice);
        }

        public Builder selectedBattery(Battery battery){
            this.battery = battery;

            return this;
        }

        public Builder withAutopilot(Autopilot autopilot) {
            this.autopilot = autopilot;

            return this;
        }

        public Electric build(){
            Electric electric = new Electric(this);
            validateCarObject(electric);
            return electric;
        }

        private void validateCarObject(Car car){
            //Tester om det er noen nullpekere på i pårevde komponenter
            if (battery == null) throw new EmptyComponentException("Du har glemt å velge et batteri");
        }
    }

    // Alle getter og INGEN setter for å gi uforanderlighet
    public Battery getBattery() {
        return battery;
    }

    public Autopilot getAutopilot() {
        return autopilot;
    }

    @Override
    public String toFile() {

        return getModel() + ";" + getModelPrice() + ";" + super.toFile() + ";" + getBattery().toFile() + ";;;;;;;"
                + super.customToFile(autopilot) + getPrice();

    }

    @Override
    public double getPrice() {
        double price = super.getPrice() + getModelPrice() + getBattery().getPrice();

        if (getAutopilot() != null) {
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
    public String toString() {

        DecimalFormat df = new DecimalFormat("###,###,###.###");
        String message = "Bilmodell: " + getModel() + "\nModellpris: " + df.format(getModelPrice()) + "kr\n\n" + super.toString() +
                "Batteri : " + getBattery().getVersion() + "\nPris: " + df.format(getBattery().getPrice()) + "kr\nBeskrivelse: " + getBattery().getDescription() + "\n\n" +
                "Tilpasninger som er valgt for konfigurasjonen: \n\n";

        message += super.testCustom(df, getAutopilot());

        message += "Totalprisen på produktet er: " + df.format(getPrice()) + "kr";
        return message;


    }
}
