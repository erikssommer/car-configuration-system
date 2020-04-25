package org.semesteroppgave.models.filehandlers.fileSave;

import org.junit.jupiter.api.Test;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.models.data.carcomponents.*;
import org.semesteroppgave.models.data.carcustomization.Autopilot;
import org.semesteroppgave.models.data.carcustomization.Gps;
import org.semesteroppgave.models.data.carcustomization.Sunroof;
import org.semesteroppgave.models.data.carcustomization.Towbar;
import org.semesteroppgave.models.data.carmodel.Electric;
import org.semesteroppgave.models.data.carmodel.Product;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileSaverCsvTest {

    @Test
    void save() {
        Product testProduct = new Electric.Builder("Elektrisk", 1_200_000)
                .selectedMotor(new Motor("V8", 2000.0, "Denne V8 motoren er billig og økonomisk"))
                .selectedRim(new Rim("Rund", 1000.0, "Disse felgene er runde"))
                .selectedSeatcover(new SeatCover("Mykt", 123, "Dette setetrekket er veldig komfortabelt under lange kjøreturer"))
                .selectedSpoiler(new Spoiler("Høy", 1000, "Denne spoileren gir ekstra fart ettersom den er meget høy"))
                .selectedTires(new Tires("Sommer", 1500.0, "Disse sommerdekkene er veldig slitesterke"))
                .selectedBattery(new Battery("Li-ion", 10000.0, "Li-ion batteri er miljøvennlige og har lang rekkevidde"))
                .withGps(new Gps())
                .withSunroof(new Sunroof())
                .withTowbar(new Towbar())
                .withAutopilot(new Autopilot())
                .build();

        ApplicationData.getInstance().getRegisterProduct().setMyProductList(testProduct);

        FileSaver fileSaver = new FileSaverCsv();

        try {
            fileSaver.save(Paths.get("files/testFiles/testWriteCsv.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}