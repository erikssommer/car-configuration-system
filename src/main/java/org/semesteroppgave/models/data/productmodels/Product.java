package org.semesteroppgave.models.data.productmodels;

import org.semesteroppgave.models.data.components.*;
import org.semesteroppgave.models.data.customizations.*;
import org.semesteroppgave.models.exceptions.EmptyComponentException;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Product is the super class that Electric, Hybrid and Diesel inherit from
 * To prevent too many input parameters we have used the builder pattern. The disadvantage is that the class is significantly larger
 * To support inherited classes of Product with builder pattern we have used 'Curiously Recurring Generic Pattern'
 */

public abstract class Product {

    private final String model; //Required
    private final double modelPrice; //Required
    private final Motor motor; //Required
    private final Rim rim; //Required
    private final SeatCover seatcover; //Required
    private final Spoiler spoiler; //Required
    private final Tires tires; //Required
    private final Custom gps; //Optional
    private final Custom sunroof; //Optional
    private final Custom towbar; //Optional

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

    @SuppressWarnings("unchecked")
    public static class Builder<T extends Builder<T>> {
        private final String model; //This is important, so we put it in the constructor
        private final double modelPrice; //This is important, so we put it in the constructor
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
            if (motor == null) throw new EmptyComponentException("You forgot to choose a motor");
            this.motor = motor;

            return (T) this;
        }

        public T selectedRim(Rim rim) {
            if (rim == null) throw new EmptyComponentException("You forgot to choose rims");
            this.rim = rim;

            return (T) this;
        }

        public T selectedSeatcover(SeatCover seatcover) {
            if (seatcover == null) throw new EmptyComponentException("You forgot to choose a seat cover");
            this.seatcover = seatcover;

            return (T) this;
        }

        public T selectedSpoiler(Spoiler spoiler) {
            if (spoiler == null) throw new EmptyComponentException("You forgot to choose a spoiler");
            this.spoiler = spoiler;

            return (T) this;
        }

        public T selectedTires(Tires tires) {
            if (tires == null) throw new EmptyComponentException("You forgot to choose tires");
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

    //All getters and NO setters to provide immutability
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

    //Method supporting the toString methods of the subclasses of Product
    //To avoid duplicate code
    public String customToString(DecimalFormat df, Custom autopilot) {
        String message = "";

        if (getGps() != null) {
            message += getGps().getCustomProperty() + "\nPrice: " + df.format(getGps().getPrice()) + "kr\n\n";
        }

        if (getSunroof() != null) {
            message += getSunroof().getCustomProperty() + "\nPrice: " + df.format(getSunroof().getPrice()) + "kr\n\n";
        }

        if (getTowbar() != null) {
            message += getTowbar().getCustomProperty() + "\nPrice: " + df.format(getTowbar().getPrice()) + "kr\n\n";
        }

        if (autopilot != null) {
            message += autopilot.getCustomProperty() + "\nPrice: " + df.format(autopilot.getPrice()) + "kr\n\n";
        }

        if (getGps() == null && getSunroof() == null && getTowbar() == null && autopilot == null) {
            message += "This configuration has no customizations\n\n";
        }
        return message;
    }

    public String customToFileCsv(Custom autopilot) {
        String message = "";

        if (getGps() != null) {
            message += getGps().toFileCsv() + ";";
        } else {
            message += ";;";
        }

        if (getSunroof() != null) {
            message += getSunroof().toFileCsv() + ";";
        } else {
            message += ";;";
        }

        if (getTowbar() != null) {
            message += getTowbar().toFileCsv() + ";";
        } else {
            message += ";;";
        }

        if (autopilot != null) {
            message += autopilot.toFileCsv() + ";";
        } else {
            message += ";;";
        }
        return message;
    }

    public String toFileCsv() {
        return getModel() + ";" + getModelPrice() + ";" + getMotor().toFile() + ";" + getRim().toFile() + ";" +
                getSeatCover().toFile() + ";" + getSpoiler().toFile() + ";" + getTires().toFile();
    }


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


    public String toString() {

        DecimalFormat df = new DecimalFormat("###,###,###.###");
        return "Car model: " + getModel() + "\nModel price: " + df.format(getModelPrice()) + "kr\n\n" +
                "Motor: " + getMotor().getVersion() + "\nPrice: " + df.format(getMotor().getPrice()) + "kr\nDescription: " + getMotor().getDescription() + "\n\n" +
                "Rim: " + getRim().getVersion() + "\nPrice: " + df.format(getRim().getPrice()) + "kr\nDescription: " + getRim().getDescription() + "\n\n" +
                "Seat cover: " + getSeatCover().getVersion() + "\nPrice: " + df.format(getSeatCover().getPrice()) + "kr\nDescription: " + getSeatCover().getDescription() + "\n\n" +
                "Spoiler: " + getSpoiler().getVersion() + "\nPrice: " + df.format(getSpoiler().getPrice()) + "kr\nDescription: " + getSpoiler().getDescription() + "\n\n" +
                "Tire: " + getTires().getVersion() + "\nPrice: " + df.format(getTires().getPrice()) + "kr\nDescription: " + getTires().getDescription() + "\n\n";

    }
}
