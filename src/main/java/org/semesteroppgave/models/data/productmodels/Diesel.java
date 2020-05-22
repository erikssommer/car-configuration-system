package org.semesteroppgave.models.data.productmodels;

import org.semesteroppgave.models.data.components.*;
import org.semesteroppgave.models.exceptions.EmptyComponentException;

import java.text.DecimalFormat;

public class Diesel extends Product {

    private final FuelContainer fuelContainer; //Required
    private final Gearbox gearbox; //Required

    protected Diesel(Builder builder) {
        super(builder);
        this.fuelContainer = builder.fuelContainer;
        this.gearbox = builder.gearbox;
    }

    public static class Builder extends Product.Builder<Builder> {
        private FuelContainer fuelContainer;
        private Gearbox gearbox;

        public Builder(String model, double modelPrice) {
            super(model, modelPrice);
        }

        public Builder selectedFuelContainer(FuelContainer fuelContainer) {
            if (fuelContainer == null) throw new EmptyComponentException("You forgot to choose a fuel container");
            this.fuelContainer = fuelContainer;

            return this;
        }

        public Builder selectedGearbox(Gearbox gearbox) {
            if (gearbox == null) throw new EmptyComponentException("You forgot to choose a gearbox");
            this.gearbox = gearbox;

            return this;
        }

        public Diesel build() {
            return new Diesel(this);
        }
    }

    //All getters and NO setters to provide immutability
    public FuelContainer getFuelContainer() {
        return fuelContainer;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    @Override
    public String toFileCsv() {

        return super.toFileCsv() + ";;;;" + getFuelContainer().toFile() + ";" +
                getGearbox().toFile() + ";" + super.customToFileCsv(null) + getTotalPrice();

    }

    @Override
    public double getTotalPrice() {
        return super.getTotalPrice() + getFuelContainer().getPrice() + getGearbox().getPrice();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Diesel) {
            Diesel product = (Diesel) obj;
            return super.equals(product) && product.getFuelContainer().equals(fuelContainer)
                    && product.getGearbox().equals(gearbox) && product.getTotalPrice() == getTotalPrice();
        }
        return false;
    }

    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("###,###,###.###");
        String message = super.toString() +
                "Fuel container: " + getFuelContainer().getVersion() + "\nPrice: " + df.format(getFuelContainer().getPrice()) + "kr\nDescription: " + getFuelContainer().getDescription() + "\n\n" +
                "Gearbox: " + getGearbox().getVersion() + "\nPrice: " + df.format(getGearbox().getPrice()) + "kr\nDescription: " + getGearbox().getDescription() + "\n\n" +
                "Customizations selected for configuration: \n\n";

        message += super.customToString(df, null);

        message += "The total price of the product is: " + df.format(getTotalPrice()) + "kr";
        return message;
    }
}
