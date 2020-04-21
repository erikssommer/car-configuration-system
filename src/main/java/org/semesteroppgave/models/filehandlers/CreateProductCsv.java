package org.semesteroppgave.models.filehandlers;

import org.semesteroppgave.models.data.carcustomization.*;
import org.semesteroppgave.models.data.carcomponents.*;
import org.semesteroppgave.models.data.carmodel.*;
import org.semesteroppgave.models.exceptions.EmptyComponentException;
import org.semesteroppgave.models.exceptions.InvalidProductException;

public class CreateProductCsv {

    private Motor motor;
    private Rim rim;
    private SeatCover seatCover;
    private Spoiler spoiler;
    private Tires tires;
    private Battery battery;
    private FuelContainer fuelContainer;

    private Gps gps;
    private Sunroof sunroof;
    private Towbar towbar;


    public Electric createElectric(String[] object) throws IllegalArgumentException {
        commonComponents(object);
        commonCustom(object);
        battery = new Battery(object[17], Double.parseDouble(object[18]), object[19]);

        Autopilot autopilot = null;
        if (object[32].equals("Autopilot")) {
            autopilot = new Autopilot();
        } else if (!object[32].isEmpty()) {
            throw new InvalidProductException("Tilpasningen: " + object[32] + " støttes ikke");
        }

        return new Electric(motor, rim, seatCover, spoiler, tires, gps, sunroof, towbar, battery, autopilot);

    }

    public Diesel createDiesel(String[] object) throws IllegalArgumentException {
        commonComponents(object);
        commonCustom(object);
        fuelContainer = new FuelContainer(object[20], Double.parseDouble(object[21]), object[22]);
        Gearbox gearbox = new Gearbox(object[23], Double.parseDouble(object[24]), object[25]);

        return new Diesel(motor, rim, seatCover, spoiler, tires, gps, sunroof, towbar, fuelContainer, gearbox);
    }

    public Hybrid createHybrid(String[] object) throws IllegalArgumentException {
        commonComponents(object);
        commonCustom(object);
        battery = new Battery(object[17], Double.parseDouble(object[18]), object[19]);
        fuelContainer = new FuelContainer(object[20], Double.parseDouble(object[21]), object[22]);

        return new Hybrid(motor, rim, seatCover, spoiler, tires, gps, sunroof, towbar, battery, fuelContainer);
    }

    private void commonComponents(String[] object) throws EmptyComponentException {
        motor = new Motor(object[2], Double.parseDouble(object[3]), object[4]);
        rim = new Rim(object[5], Double.parseDouble(object[6]), object[7]);
        seatCover = new SeatCover(object[8], Double.parseDouble(object[9]), object[10]);
        spoiler = new Spoiler(object[11], Double.parseDouble(object[12]), object[13]);
        tires = new Tires(object[14], Double.parseDouble(object[15]), object[16]);
    }

    private void commonCustom(String[] object) throws InvalidProductException {
        //Nulstiller tilpasningene ettersom det er mulig at produktet ikke har alle valgt
        gps = null;
        towbar = null;
        sunroof = null;
        //Tester om produktet skal ha tilpasningen
        if (object[26].equals("GPS-system")) {
            gps = new Gps();
        } else if (!object[26].isEmpty()) {
            throw new InvalidProductException("Tilpasningen: " + object[26] + " støttes ikke");
        }

        if (object[28].equals("Soltak")) {
            sunroof = new Sunroof();
        } else if (!object[28].isEmpty()) {
            throw new InvalidProductException("Tilpasningen: " + object[28] + " støttes ikke");
        }

        if (object[30].equals("Tillhengerfeste")) {
            towbar = new Towbar();
        } else if (!object[30].isEmpty()) {
            throw new InvalidProductException("Tilpasningen: " + object[30] + " støttes ikke");
        }
    }
}
