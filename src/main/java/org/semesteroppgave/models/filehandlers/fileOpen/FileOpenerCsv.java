package org.semesteroppgave.models.filehandlers.fileOpen;


import org.semesteroppgave.Context;
import org.semesteroppgave.models.data.carmodel.*;
import org.semesteroppgave.models.exceptions.*;
import org.semesteroppgave.models.filehandlers.CreateProductCsv;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileOpenerCsv implements FileOpener {

    CreateProductCsv create = new CreateProductCsv();

    @Override
    public void open(Path filePath) throws IOException {
        Context.getInstance().getRegisterProduct().getCarList().clear();
        // try-with-resources lukker automatisk filen
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            String line;
            //Hopper over menylinjen
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                Context.getInstance().getRegisterProduct().setCarList(parseProduct(line));
            }
        }catch (NullPointerException | NumberFormatException | InvalidProductException | InvalidComponentException | InvalidDescriptionException | InvalidPriceException | InvalidVersionException e){
            Dialogs.showErrorDialog("Oups", "Feil i innlasting fra cvs-fil", e.getMessage());

        }
    }

    private Car parseProduct(String line) throws IllegalArgumentException{
        Car carRead = null;
        String[] split = line.split(";");

        if (split.length != 35){
            throw new InvalidProductException("Feil antall kolonner. Bruk ; for å skille de");
        }

        switch (split[0]){
            case "Elektrisk": carRead = create.createElectric(split);
            break;
            case "Diesel": carRead = create.createDiesel(split);
            break;
            case "Hybrid": carRead = create.createHybrid(split);
            break;
            default: throw new InvalidProductException("Modellen "+split[0]+" finnes ikke");
        }

        return carRead;
    }
}
