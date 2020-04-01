package org.semesteroppgave.carmodel;

import org.semesteroppgave.carcustomization.Gps;
import org.semesteroppgave.carcustomization.Sunroof;
import org.semesteroppgave.carcustomization.Towbar;
import org.semesteroppgave.carcomponents.*;

import java.io.Serializable;

public abstract class Car implements Serializable {
    private Motor motor;
    private Rim rim;
    private SeatCover seatcover;
    private Spoiler spoiler;
    private Tires tires;
    private Gps gps;
    private Sunroof sunroof;
    private Towbar towbar;
    private double totalPrice;

    public Car(Motor motor, Rim rim, SeatCover seatcover, Spoiler spoiler, Tires tires, Gps gps, Sunroof sunroof, Towbar towbar) {
        if (motor == null || rim == null || spoiler == null || tires == null) throw new NullPointerException("Du har glemt å opprette en eller flere komponenter");
        this.motor = motor;
        this.rim = rim;
        this.seatcover = seatcover;
        this.spoiler = spoiler;
        this.tires = tires;
        this.gps = gps;
        this.sunroof = sunroof;
        this.towbar = towbar;
    }

    public abstract double getPrice();

    public Motor getMotor(){
        return this.motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Rim getRim() {
        return rim;
    }

    public void setRim(Rim rim) {
        this.rim = rim;
    }

    public SeatCover getSeatCover() {
        return seatcover;
    }

    public void setSeatCover(SeatCover seatcover) {
        this.seatcover = seatcover;
    }

    public Spoiler getSpoiler() {
        return spoiler;
    }

    public void setSpoiler(Spoiler spoiler) {
        this.spoiler = spoiler;
    }

    public Tires getTires() {
        return tires;
    }

    public void setTires(Tires tires) {
        this.tires = tires;
    }

    public Gps getGps() {
        return this.gps;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }

    public Sunroof getSunroof() {
        return this.sunroof;
    }

    public void setSunroof(Sunroof sunroof) {
        this.sunroof = sunroof;
    }

    public Towbar getTowbar() {
        return this.towbar;
    }

    public void setTowbar(Towbar towbar) {
        this.towbar = towbar;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice){
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString(){
        return "Motor: "+getMotor().getVersion()+"\nPris: "+ getMotor().getPrice()+"\nBeskrivelse: "+getMotor().getDescription()+"\n\n"+
                "Felg: "+getRim().getVersion()+"\nPris: "+ getRim().getPrice()+"\nBeskrivelse: "+getRim().getDescription()+"\n\n"+
                "Setetrekk: "+getSeatCover().getVersion()+"\nPris: "+ getSeatCover().getPrice()+"\nBeskrivelse: "+getSeatCover().getDescription()+"\n\n"+
                "Spoiler: "+getSpoiler().getVersion()+"\nPris: "+ getSpoiler().getPrice()+"\nBeskrivelse: "+getSpoiler().getDescription()+"\n\n"+
                "Dekk: "+getTires().getVersion()+"\nPris: "+ getTires().getPrice()+"\nBeskrivelse: "+getTires().getDescription()+"\n\n";
    }
}
