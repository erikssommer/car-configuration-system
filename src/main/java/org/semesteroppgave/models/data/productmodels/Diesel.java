package org.semesteroppgave.models.data.productmodels;

import org.semesteroppgave.models.data.components.*;
import org.semesteroppgave.models.exceptions.EmptyComponentException;

import java.text.DecimalFormat;

public class Diesel extends Product {

    private final FuelContainer fuelContainer; //Påkreves
    private final Gearbox gearbox; //Påkreves

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
            if (fuelContainer == null) throw new EmptyComponentException("Du har glemt å velge en tank");
            this.fuelContainer = fuelContainer;

            return this;
        }

        public Builder selectedGearbox(Gearbox gearbox){
            if (gearbox == null) throw new EmptyComponentException("Du har glemt å velge en girboks");
            this.gearbox = gearbox;

            return this;
        }

        public Diesel build(){
            return new Diesel(this);
        }
    }

    // Alle getter og INGEN setter for å gi uforanderlighet
    public FuelContainer getFuelContainer() {
        return fuelContainer;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    @Override
    public String toFileCsv() {

        return super.toFileCsv() + ";;;;" + getFuelContainer().toFile() + ";" +
                getGearbox().toFile() + ";" + super.customToFile(null) + getTotalPrice();

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
                "Tank: " + getFuelContainer().getVersion() + "\nPris: " + df.format(getFuelContainer().getPrice()) + "kr\nBeskrivelse: " + getFuelContainer().getDescription() + "\n\n" +
                "Girboks: " + getGearbox().getVersion() + "\nPris: " + df.format(getGearbox().getPrice()) + "kr\nBeskrivelse: " + getGearbox().getDescription() + "\n\n" +
                "Tilpasninger som er valgt for konfigurasjonen: \n\n";

        message += super.testCustom(df, null);

        message += "Totalprisen på produktet er: " + df.format(getTotalPrice()) + "kr";
        return message;

    }
}
