package org.semesteroppgave.models.filehandlers.fileOpen;

import org.junit.jupiter.api.Test;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.models.data.carcomponents.*;
import org.semesteroppgave.models.data.carcustomization.Autopilot;
import org.semesteroppgave.models.data.carcustomization.Gps;
import org.semesteroppgave.models.data.carcustomization.Sunroof;
import org.semesteroppgave.models.data.carcustomization.Towbar;
import org.semesteroppgave.models.data.carmodel.Diesel;
import org.semesteroppgave.models.data.carmodel.Electric;
import org.semesteroppgave.models.data.carmodel.Hybrid;
import org.semesteroppgave.models.data.carmodel.Product;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CreateProductCsvTest {

    @Test
    void createElectric() {

        Product electric = new Electric.Builder("Elektrisk", 1_200_000)
                .selectedMotor(new Motor("V12", 12300.0, "Denne V12 motoren er slitesterk og har en veldig høy ytelse"))
                .selectedRim(new Rim("Feit", 3900.0, "Disse felgene er laget av aluminium som gir en fin glans i solen"))
                .selectedSeatcover(new SeatCover("Mykt", 123, "Dette setetrekket er veldig komfortabelt under lange kjøreturer"))
                .selectedSpoiler(new Spoiler("Høy", 1000, "Denne spoileren gir ekstra fart ettersom den er meget høy"))
                .selectedTires(new Tires("Sommer", 1500.0, "Disse sommerdekkene er veldig slitesterke"))
                .selectedBattery(new Battery("Li-ion", 10000.0, "Li-ion batteri er miljøvennlige og har lang rekkevidde"))
                .withGps(new Gps())
                .withAutopilot(new Autopilot())
                .build();

        Product electricFromFile = testParcer(Paths.get("files/testFiles/testElektrisk.csv"), "Elektrisk");

        assertEquals(electric.toString(), electricFromFile.toString());
    }

    @Test
    void createDiesel() {

        Product diesel = new Diesel.Builder("Diesel", 400_000)
                .selectedMotor(new Motor("V12", 12300.0, "Denne V12 motoren er slitesterk og har en veldig høy ytelse"))
                .selectedRim(new Rim("Feit", 3900.0, "Disse felgene er laget av aluminium som gir en fin glans i solen"))
                .selectedSeatcover(new SeatCover("Mykt", 123, "Dette setetrekket er veldig komfortabelt under lange kjøreturer"))
                .selectedSpoiler(new Spoiler("Høy", 1000, "Denne spoileren gir ekstra fart ettersom den er meget høy"))
                .selectedTires(new Tires("Sommer", 1500.0, "Disse sommerdekkene er veldig slitesterke"))
                .selectedGearbox(new Gearbox("Automat", 5000, "Automat girboks er dyrere enn manuell, men er mer komportabelt"))
                .selectedFuelContainer(new FuelContainer("100-liter", 10000, "Denne tanken er stor og har god kondens"))
                .withSunroof(new Sunroof())
                .build();

        Product dieselFromFile = testParcer(Paths.get("files/testFiles/testDiesel.csv"), "Diesel");

        assertEquals(diesel.toString(), dieselFromFile.toString());
    }

    @Test
    void createHybrid() {
        //Her tester vi at de to objektene er ulike
        Product hybrid = new Hybrid.Builder("Hybrid", 850_000)
                .selectedMotor(new Motor("V12", 12300.0, "Denne V12 motoren er slitesterk og har en veldig høy ytelse"))
                .selectedRim(new Rim("Feit", 3900.0, "Disse felgene er laget av aluminium som gir en fin glans i solen"))
                .selectedSeatcover(new SeatCover("Mykt", 123, "Dette setetrekket er veldig komfortabelt under lange kjøreturer"))
                .selectedSpoiler(new Spoiler("Høy", 1000, "Denne spoileren gir ekstra fart ettersom den er meget høy"))
                .selectedTires(new Tires("Sommer", 1500.0, "Disse sommerdekkene er veldig slitesterke"))
                .selectedBattery(new Battery("Li-ion", 10000.0, "Li-ion batteri er miljøvennlige og har lang rekkevidde"))
                .selectedFuelContainer(new FuelContainer("100-liter", 10000, "Denne tanken er stor og har god kondens"))
                .build();

        Product hybridFromFile = testParcer(Paths.get("files/testFiles/testHybrid.csv"), "Hybrid");


        assertNotEquals(hybrid.toString(), hybridFromFile.toString());

    }

    //Ettersom metoden 'open' i FileOpener returnerer void,
    //har vi implementert samme metode bare at den returnerer et objekt for å kunne gjennomføre test
    private Product testParcer(Path path, String model){

        CreateProductCsv createProductCsv = new CreateProductCsv();

        Product product = null;

        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            //Hopper over menylinjen
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(";");
                switch (model){
                    case "Elektrisk" : product = createProductCsv.createElectric(split);
                    break;
                    case "Diesel" : product = createProductCsv.createDiesel(split);
                    break;
                    case "Hybrid" : product = createProductCsv.createHybrid(split);
                }
            }
        } catch (IllegalArgumentException | IOException e) {
            System.err.println(e.getMessage());
        }

        return product;
    }

}