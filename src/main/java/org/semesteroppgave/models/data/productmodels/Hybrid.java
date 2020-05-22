package org.semesteroppgave.models.data.productmodels;

import org.semesteroppgave.models.data.components.*;
import org.semesteroppgave.models.exceptions.EmptyComponentException;

import java.text.DecimalFormat;

public class Hybrid extends Product {
    private final Battery battery; //Required
    private final FuelContainer fuelContainer; //Required

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

        public Builder selectedBattery(Battery battery) {
            if (battery == null) throw new EmptyComponentException("You forgot to choose a battery");
            this.battery = battery;

            return this;
        }

        public Builder selectedFuelContainer(FuelContainer fuelContainer) {
            if (fuelContainer == null) throw new EmptyComponentException("You forgot to choose a fuel container");
            this.fuelContainer = fuelContainer;

            return this;
        }

        public Hybrid build() {
            return new Hybrid(this);
        }
    }

    //All getters and NO setters to provide immutability
    public Battery getBattery() {
        return battery;
    }

    public FuelContainer getFuelContainer() {
        return fuelContainer;
    }

    @Override
    public String toFileCsv() {

        return super.toFileCsv() + ";" + getBattery().toFile() + ";" +
                getFuelContainer().toFile() + ";;;;" + super.customToFileCsv(null) + getTotalPrice();

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
                "Battery : " + getBattery().getVersion() + "\nPrice: " + df.format(getBattery().getPrice()) + "kr\nDescription: " + getBattery().getDescription() + "\n\n" +
                "Fuel container : " + getFuelContainer().getVersion() + "\nPrice: " + df.format(getFuelContainer().getPrice()) + "kr\nDescription: " + getFuelContainer().getDescription() + "\n\n" +
                "Customizations selected for the configuration: \n\n";

        message += super.customToString(df, null);

        message += "The total price of the product is: " + df.format(getTotalPrice()) + "kr";
        return message;
    }
}
