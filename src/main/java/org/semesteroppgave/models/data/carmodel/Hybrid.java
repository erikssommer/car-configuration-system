package org.semesteroppgave.models.data.carmodel;

import org.semesteroppgave.models.data.carcomponents.*;
import org.semesteroppgave.models.exceptions.EmptyComponentException;

import java.text.DecimalFormat;

public class Hybrid extends Product {
    private final Battery battery;
    private final FuelContainer fuelContainer;

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
            this.battery = battery;

            return this;
        }

        public Builder selectedFuelContainer(FuelContainer fuelContainer) {
            this.fuelContainer = fuelContainer;

            return this;
        }

        public Hybrid build(){
            Hybrid hybrid = new Hybrid(this);
            validateCarObject(hybrid);
            return hybrid;
        }

        private void validateCarObject(Product product){
            //Tester om det er noen nullpekere på i pårevde komponenter
            if (fuelContainer == null) throw new EmptyComponentException("Du har glemt å velge en tank");
            if (battery == null) throw new EmptyComponentException("Du har glemt å velge et batteri");
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
    public String toFile() {

        return getModel() + ";" + getModelPrice() + ";" + super.toFile() + ";" + getBattery().toFile() + ";" +
                getFuelContainer().toFile() + ";;;;" + super.customToFile(null) + getPrice();

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
    public String toString() {

        DecimalFormat df = new DecimalFormat("###,###,###.###");
        String message = "Bilmodell: " + getModel() + "\nModellpris: " + df.format(getModelPrice()) + "kr\n\n" + super.toString() +
                "Batteri : " + getBattery().getVersion() + "\nPris: " + df.format(getBattery().getPrice()) + "kr\nBeskrivelse: " + getBattery().getDescription() + "\n\n" +
                "Tank : " + getFuelContainer().getVersion() + "\nPris: " + df.format(getFuelContainer().getPrice()) + "kr\nBeskrivelse: " + getFuelContainer().getDescription() + "\n\n" +
                "Tilpasninger som er valgt for konfigurasjonen: \n\n";

        message += super.testCustom(df, null);

        message += "Totalprisen på produktet er: " + df.format(getPrice()) + "kr";
        return message;


    }
}
