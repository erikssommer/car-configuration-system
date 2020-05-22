package org.semesteroppgave.models.data.productmodels;

import org.semesteroppgave.models.data.components.*;
import org.semesteroppgave.models.data.customizations.*;
import org.semesteroppgave.models.exceptions.EmptyComponentException;

import java.text.DecimalFormat;
import java.util.Objects;

public class Electric extends Product {

    private final Battery battery; //Required
    private final Custom autopilot; //Optional

    public Electric(Builder builder) {
        super(builder);
        this.battery = builder.battery;
        this.autopilot = builder.autopilot;
    }

    public static class Builder extends Product.Builder<Builder> {
        private Battery battery;
        private Custom autopilot;

        public Builder(String model, double modelPrice) {
            super(model, modelPrice);
        }

        public Builder selectedBattery(Battery battery) {
            if (battery == null) throw new EmptyComponentException("You forgot to choose a battery");
            this.battery = battery;

            return this;
        }

        public Builder withAutopilot(Custom autopilot) {
            this.autopilot = autopilot;

            return this;
        }

        public Electric build() {
            return new Electric(this);
        }
    }

    //All getters and NO setters to provide immutability
    public Battery getBattery() {
        return battery;
    }

    public Custom getAutopilot() {
        return autopilot;
    }

    @Override
    public String toFileCsv() {

        return super.toFileCsv() + ";" + getBattery().toFile() + ";;;;;;;"
                + super.customToFileCsv(autopilot) + getTotalPrice();

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
                    && ((product.getAutopilot() == null && autopilot == null) || Objects.equals(product.getAutopilot(), autopilot));
        }
        return false;
    }

    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("###,###,###.###");
        String message = super.toString() +
                "Battery : " + getBattery().getVersion() + "\nPrice: " + df.format(getBattery().getPrice()) + "kr\nDescription: " + getBattery().getDescription() + "\n\n" +
                "Customizations selected for the configuration: \n\n";

        message += super.customToString(df, getAutopilot());

        message += "The total price of the product is: " + df.format(getTotalPrice()) + "kr";
        return message;
    }
}
