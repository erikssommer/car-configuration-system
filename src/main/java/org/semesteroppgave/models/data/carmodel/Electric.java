package org.semesteroppgave.models.data.carmodel;

import org.semesteroppgave.models.data.carcomponents.*;
import org.semesteroppgave.models.data.carcustomization.*;
import org.semesteroppgave.models.exceptions.EmptyComponentException;

import java.text.DecimalFormat;

public class Electric extends Product {

    private final Battery battery; //Påkreves
    private final Autopilot autopilot; //Valgfri

    public Electric(Builder builder) {
        super(builder);
        this.battery = builder.battery;
        this.autopilot = builder.autopilot;
    }

    public static class Builder extends Product.Builder<Builder> {
        private Battery battery;
        private Autopilot autopilot;

        public Builder(String model, double modelPrice) {
            super(model, modelPrice);
        }

        public Builder selectedBattery(Battery battery){
            if (battery == null) throw new EmptyComponentException("Du har glemt å velge et batteri");
            this.battery = battery;

            return this;
        }

        public Builder withAutopilot(Autopilot autopilot) {
            this.autopilot = autopilot;

            return this;
        }

        public Electric build(){
            return new Electric(this);
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
    public String toFileCsv() {

        return super.toFileCsv() + ";" + getBattery().toFile() + ";;;;;;;"
                + super.customToFile(autopilot) + getTotalPrice();

    }

    @Override
    public double getTotalPrice() {
        double price = super.getTotalPrice() + getBattery().getPrice();

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
        String message = super.toString() +
                "Batteri : " + getBattery().getVersion() + "\nPris: " + df.format(getBattery().getPrice()) + "kr\nBeskrivelse: " + getBattery().getDescription() + "\n\n" +
                "Tilpasninger som er valgt for konfigurasjonen: \n\n";

        message += super.testCustom(df, getAutopilot());

        message += "Totalprisen på produktet er: " + df.format(getTotalPrice()) + "kr";
        return message;


    }
}
