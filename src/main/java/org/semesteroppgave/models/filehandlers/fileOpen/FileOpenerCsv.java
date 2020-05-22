package org.semesteroppgave.models.filehandlers.fileOpen;


import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.models.data.productmodels.*;
import org.semesteroppgave.models.exceptions.*;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class that reads from csv file and creates products
 */

public class FileOpenerCsv implements FileOpener {

    private final CreateProductCsv create = new CreateProductCsv();

    @Override
    public void open(Path filePath) throws IOException {
        ApplicationData.getInstance().getRegisterProduct().getProductList().clear();
        //try-with-resources automatically closes the file
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            String line;
            //Skip the menu bar
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                ApplicationData.getInstance().getRegisterProduct().setProductList(parseProduct(line));
            }

        } catch (InvalidProductException | InvalidPriceException | InvalidVersionException | InvalidDescriptionException | EmptyComponentException e) {
            Dialogs.showErrorDialog("Oups!", "Feil i innlasting fra cvs-fil", e.getMessage() +
                    "\n\nLaster inn vellykkede produkter frem til feilen oppsto");
        } catch (NumberFormatException nfe) {
            Dialogs.showErrorDialog("Oups!", "Feil i innlasting fra cvs-fil",
                    "Prisen må være fyllt inn og være et tall" +
                            "\n\nLaster inn vellykkede produkter frem til feilen oppsto");
        }
    }

    /**
     * @param line series that split with ;
     * @return returns the product
     * @throws IllegalArgumentException if there is wrong information in the csv file
     */
    private Product parseProduct(String line) throws IllegalArgumentException {
        Product productRead;
        String[] split = line.split(";");

        if (split.length != 35) {
            throw new InvalidProductException("Feil antall kolonner. Bruk ; for å skille de");
        }

        switch (split[0]) {
            case "Elektrisk":
                productRead = create.createElectric(split);
                break;
            case "Diesel":
                productRead = create.createDiesel(split);
                break;
            case "Hybrid":
                productRead = create.createHybrid(split);
                break;
            default:
                throw new InvalidProductException("Modellen " + split[0] + " finnes ikke");
        }

        return productRead;
    }
}
