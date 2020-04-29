package org.semesteroppgave.models.data.productmodels;

import org.semesteroppgave.models.data.components.*;
import org.semesteroppgave.models.data.customizations.*;
import org.semesteroppgave.models.exceptions.EmptyComponentException;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Product er superklassen som Electric, Hybrid og Diesel arver fra
 * For å forhindre alt for mange innparametere har vi brukt builder-pattern
 * For å støtte arvede klasser av Product har vi brukt 'Curiously Recurring Generic Pattern'
 */

public abstract class Product {

    private final String model; //Påkreves
    private final double modelPrice; //Påkreves
    private final Motor motor; //Påkreves
    private final Rim rim; //Påkreves
    private final SeatCover seatcover; //Påkreves
    private final Spoiler spoiler; //Påkreves
    private final Tires tires; //Påkreves
    private final Custom gps; //Valgfri
    private final Custom sunroof; //Valgfri
    private final Custom towbar; //Valgfri

    protected Product(Builder<?> builder) {
        this.model = builder.model;
        this.modelPrice = builder.modelPrice;
        this.motor = builder.motor;
        this.rim = builder.rim;
        this.seatcover = builder.seatcover;
        this.spoiler = builder.spoiler;
        this.tires = builder.tires;
        this.gps = builder.gps;
        this.sunroof = builder.sunroof;
        this.towbar = builder.towbar;
    }

    @SuppressWarnings("unchecked")//'return (T) this;' gir en 'Unchecked cast', men i dette tilfellet vil ikke bli kastet et avvik
    public static class Builder<T extends Builder<T>> {
        private final String model; //Denne er viktig, så den legger vi i konstruktøren
        private final double modelPrice; //Denne er viktig, så den legger vi i konstruktøren
        private Motor motor;
        private Rim rim;
        private SeatCover seatcover;
        private Spoiler spoiler;
        private Tires tires;
        private Custom gps;
        private Custom sunroof;
        private Custom towbar;

        public Builder(String model, double modelPrice) {
            this.model = model;
            this.modelPrice = modelPrice;
        }

        public T selectedMotor(Motor motor) {
            if (motor == null) throw new EmptyComponentException("Du har glemt å velge en motor");
            this.motor = motor;

            return (T) this;
        }

        public T selectedRim(Rim rim) {
            if (rim == null) throw new EmptyComponentException("Du har glemt å velge felger");
            this.rim = rim;

            return (T) this;
        }

        public T selectedSeatcover(SeatCover seatcover) {
            if (seatcover == null) throw new EmptyComponentException("Du har glemt å velge setetrekk");
            this.seatcover = seatcover;

            return (T) this;
        }

        public T selectedSpoiler(Spoiler spoiler) {
            if (spoiler == null) throw new EmptyComponentException("Du har glemt å velge en spoiler");
            this.spoiler = spoiler;

            return (T) this;
        }

        public T selectedTires(Tires tires) {
            if (tires == null) throw new EmptyComponentException("Du har glemt å velge dekk");
            this.tires = tires;

            return (T) this;
        }

        public T withGps(Custom gps) {
            this.gps = gps;

            return (T) this;
        }

        public T withSunroof(Custom sunroof) {
            this.sunroof = sunroof;

            return (T) this;
        }

        public T withTowbar(Custom towbar) {
            this.towbar = towbar;

            return (T) this;
        }

    }

    // Alle getter og INGEN setter for å gi uforanderlighet
    public String getModel() {
        return this.model;
    }

    public double getModelPrice() {
        return this.modelPrice;
    }

    public Motor getMotor() {
        return this.motor;
    }

    public Rim getRim() {
        return rim;
    }

    public SeatCover getSeatCover() {
        return seatcover;
    }

    public Spoiler getSpoiler() {
        return spoiler;
    }

    public Tires getTires() {
        return tires;
    }

    public Custom getGps() {
        return this.gps;
    }

    public Custom getSunroof() {
        return this.sunroof;
    }

    public Custom getTowbar() {
        return this.towbar;
    }


    public double getTotalPrice() {

        double price = getModelPrice() + getMotor().getPrice() + getRim().getPrice() + getSeatCover().getPrice() +
                getSpoiler().getPrice() + getTires().getPrice();
        if (getGps() != null) {
            price += getGps().getPrice();
        }
        if (getSunroof() != null) {
            price += getSunroof().getPrice();
        }
        if (getTowbar() != null) {
            price += getTowbar().getPrice();
        }

        return price;
    }

    //Metode som støtter toString metodene til underklassene til Car
    //For å unngå duplikat kode
    public String testCustom(DecimalFormat df, Custom autopilot) {

        String message = "";
        if (getGps() != null) {
            message += getGps().getCustomProperty() + "\nPris: " + df.format(getGps().getPrice()) + "kr\n\n";
        }
        if (getSunroof() != null) {
            message += getSunroof().getCustomProperty() + "\nPris: " + df.format(getSunroof().getPrice()) + "kr\n\n";
        }
        if (getTowbar() != null) {
            message += getTowbar().getCustomProperty() + "\nPris: " + df.format(getTowbar().getPrice()) + "kr\n\n";
        }

        if (autopilot != null) {
            message += autopilot.getCustomProperty() + "\nPris: " + df.format(autopilot.getPrice()) + "kr\n\n";
        }

        if (getGps() == null && getSunroof() == null && getTowbar() == null && autopilot == null) {
            message += "Denne komfigurasjonen har ingen tilpasninger\n\n";
        }
        return message;
    }

    public String customToFile(Custom autopilot) {
        String message = "";
        if (getGps() != null) {
            message += getGps().toFile() + ";";
        } else {
            message += ";;";
        }
        if (getSunroof() != null) {
            message += getSunroof().toFile() + ";";
        } else {
            message += ";;";
        }
        if (getTowbar() != null) {
            message += getTowbar().toFile() + ";";
        } else {
            message += ";;";
        }

        if (autopilot != null) {
            message += autopilot.toFile() + ";";
        } else {
            message += ";;";
        }
        return message;
    }

    public String toFileCsv() {
        return getModel() + ";" + getModelPrice() + ";" + getMotor().toFile() + ";" + getRim().toFile() + ";" +
                getSeatCover().toFile() + ";" + getSpoiler().toFile() + ";" + getTires().toFile();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (obj instanceof Product) {
            Product product = (Product) obj;
            return product.getMotor().equals(motor) && product.getRim().equals(rim)
                    && product.getSeatCover().equals(seatcover)
                    && product.getSpoiler().equals(spoiler) && product.getTires().equals(tires)
                    && ((product.getGps() == null && gps == null) || Objects.equals(product.getGps(), gps))
                    && ((product.getSunroof() == null && sunroof == null) || Objects.equals(product.getSunroof(), sunroof))
                    && ((product.getTowbar() == null && towbar == null) || Objects.equals(product.getTowbar(), towbar));
        }
        return false;
    }


    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("###,###,###.###");
        return "Bilmodell: " + getModel() + "\nModellpris: " + df.format(getModelPrice()) + "kr\n\n" +
                "Motor: " + getMotor().getVersion() + "\nPris: " + df.format(getMotor().getPrice()) + "kr\nBeskrivelse: " + getMotor().getDescription() + "\n\n" +
                "Felg: " + getRim().getVersion() + "\nPris: " + df.format(getRim().getPrice()) + "kr\nBeskrivelse: " + getRim().getDescription() + "\n\n" +
                "Setetrekk: " + getSeatCover().getVersion() + "\nPris: " + df.format(getSeatCover().getPrice()) + "kr\nBeskrivelse: " + getSeatCover().getDescription() + "\n\n" +
                "Spoiler: " + getSpoiler().getVersion() + "\nPris: " + df.format(getSpoiler().getPrice()) + "kr\nBeskrivelse: " + getSpoiler().getDescription() + "\n\n" +
                "Dekk: " + getTires().getVersion() + "\nPris: " + df.format(getTires().getPrice()) + "kr\nBeskrivelse: " + getTires().getDescription() + "\n\n";

    }
}
