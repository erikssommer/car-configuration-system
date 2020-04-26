package org.semesteroppgave.models.filehandlers.fileOpen;

import org.junit.jupiter.api.Test;
import org.semesteroppgave.Main;
import org.semesteroppgave.models.data.productcomponents.*;
import org.semesteroppgave.models.data.productcustomization.*;
import org.semesteroppgave.models.data.productmodel.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CreateProductCsvTest {

    @Test
    void createElectric() {
        //Oppretter nytt electric objekt
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

        //Leser inn elektisk-objekt fra fil
        Product electricFromFile = testParcer(Paths.get(Main.class.getResource("files/testFiles/testElektrisk.csv").getFile()), "Elektrisk");
        //Tester om de er like
        assertEquals(electric.toString(), electricFromFile.toString());
    }

    @Test
    void createDiesel() {
        //Oppretter et nytt diesel-objekt
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

        //Leser inn diesel-objekt fra fil
        Product dieselFromFile = testParcer(Paths.get(Main.class.getResource("files/testFiles/testDiesel.csv").getFile()), "Diesel");
        //Tester om de er like
        assertEquals(diesel.toString(), dieselFromFile.toString());
    }

    //Her tester vi at de to objektene er ulike
    @Test
    void createHybrid() {
        //Oppretter et nytt hybrid-objekt
        Product hybrid = new Hybrid.Builder("Hybrid", 850_000)
                .selectedMotor(new Motor("V12", 12300.0, "Denne V12 motoren er slitesterk og har en veldig høy ytelse"))
                .selectedRim(new Rim("Feit", 3900.0, "Disse felgene er laget av aluminium som gir en fin glans i solen"))
                .selectedSeatcover(new SeatCover("Mykt", 123, "Dette setetrekket er veldig komfortabelt under lange kjøreturer"))
                .selectedSpoiler(new Spoiler("Høy", 1000, "Denne spoileren gir ekstra fart ettersom den er meget høy"))
                .selectedTires(new Tires("Sommer", 1500.0, "Disse sommerdekkene er veldig slitesterke"))
                .selectedBattery(new Battery("Li-ion", 10000.0, "Li-ion batteri er miljøvennlige og har lang rekkevidde"))
                .selectedFuelContainer(new FuelContainer("100-liter", 10000, "Denne tanken er stor og har god kondens"))
                .build();

        //Leser inn hybrid-objekt fra fil
        Product hybridFromFile = testParcer(Paths.get(Main.class.getResource("files/testFiles/testHybrid.csv").getFile()), "Hybrid");

        //Tester om de er like
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