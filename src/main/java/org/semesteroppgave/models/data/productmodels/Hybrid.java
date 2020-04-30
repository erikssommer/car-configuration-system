package org.semesteroppgave.models.data.productmodels;

import org.semesteroppgave.models.data.components.*;
import org.semesteroppgave.models.exceptions.EmptyComponentException;

import java.text.DecimalFormat;

public class Hybrid extends Product {
    private final Battery battery; //Påkreves
    private final FuelContainer fuelContainer; //Påkreves

    protected Hybrid(Builder builder) {
        super(builder);
        this.battery = builder.battery;
        this.fuelContainer = builder.fuelContainer;
    }

    public static class Builder extends Product.Builder<Builder> {
        private Battery battery;
        private FuelContainer fuelContainer;

        public Builder(String model, double modelPrice) {
            super(model, modelPrice);
        }

        public Builder selectedBattery(Battery battery){
            if (battery == null) throw new EmptyComponentException("Du har glemt å velge et batteri");
            this.battery = battery;

            return this;
        }

        public Builder selectedFuelContainer(FuelContainer fuelContainer) {
            if (fuelContainer == null) throw new EmptyComponentException("Du har glemt å velge en tank");
            this.fuelContainer = fuelContainer;

            return this;
        }

        public Hybrid build(){
            return new Hybrid(this);
        }
    }

    // Alle getter og INGEN setter for å gi uforanderlighet
    public Battery getBattery() {
        return battery;
    }

    public FuelContainer getFuelContainer() {
        return fuelContainer;
    }

    @Override
    public String toFileCsv() {

        return super.toFileCsv() + ";" + getBattery().toFile() + ";" +
                getFuelContainer().toFile() + ";;;;" + super.customToFile(null) + getTotalPrice();

    }

    @Override
    public double getTotalPrice() {

        return super.getTotalPrice() + getBattery().getPrice() + getFuelContainer().getPrice();

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Hybrid) {
            Hybrid product = (Hybrid) obj;
            return super.equals(product) && product.getBattery().equals(battery)
                    && product.getFuelContainer().equals(fuelContainer) && product.getTotalPrice() == getTotalPrice();
        }
        return false;
    }

    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("###,###,###.###");
        String message = super.toString() +
                "Batteri : " + getBattery().getVersion() + "\nPris: " + df.format(getBattery().getPrice()) + "kr\nBeskrivelse: " + getBattery().getDescription() + "\n\n" +
                "Tank : " + getFuelContainer().getVersion() + "\nPris: " + df.format(getFuelContainer().getPrice()) + "kr\nBeskrivelse: " + getFuelContainer().getDescription() + "\n\n" +
                "Tilpasninger som er valgt for konfigurasjonen: \n\n";

        message += super.testCustom(df, null);

        message += "Totalprisen på produktet er: " + df.format(getTotalPrice()) + "kr";
        return message;
    }
}
