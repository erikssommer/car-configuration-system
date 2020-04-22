package org.semesteroppgave.models.data.carmodel;

import org.semesteroppgave.models.data.carcomponents.*;
import org.semesteroppgave.models.exceptions.EmptyComponentException;

import java.text.DecimalFormat;

public class Diesel extends Product {

    private final FuelContainer fuelContainer;
    private final Gearbox gearbox;

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
            this.fuelContainer = fuelContainer;

            return this;
        }

        public Builder selectedGearbox(Gearbox gearbox){
            this.gearbox = gearbox;

            return this;
        }

        public Diesel build(){
            Diesel diesel = new Diesel(this);
            validateCarObject(diesel);
            return diesel;
        }

        private void validateCarObject(Product product){
            //Tester om det er noen nullpekere på i pårevde komponenter
            if (fuelContainer == null) throw new EmptyComponentException("Du har glemt å velge en tank");
            if (gearbox == null) throw new EmptyComponentException("Du har glemt å velge en girboks");
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
    public String toFile() {

        return getModel() + ";" + getModelPrice() + ";" + super.toFile() + ";;;;" + getFuelContainer().toFile() + ";" +
                getGearbox().toFile() + ";" + super.customToFile(null) + getPrice();

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
    public String toString() {

        DecimalFormat df = new DecimalFormat("###,###,###.###");
        String message = "Bilmodell: " + getModel() + "\nModellpris: " + df.format(getModelPrice()) + "kr\n\n" + super.toString() +
                "Tank: " + getFuelContainer().getVersion() + "\nPris: " + df.format(getFuelContainer().getPrice()) + "kr\nBeskrivelse: " + getFuelContainer().getDescription() + "\n\n" +
                "Girboks: " + getGearbox().getVersion() + "\nPris: " + df.format(getGearbox().getPrice()) + "kr\nBeskrivelse: " + getGearbox().getDescription() + "\n\n" +
                "Tilpasninger som er valgt for konfigurasjonen: \n\n";

        message += super.testCustom(df, null);

        message += "Totalprisen på produktet er: " + df.format(getPrice()) + "kr";
        return message;

    }
}
