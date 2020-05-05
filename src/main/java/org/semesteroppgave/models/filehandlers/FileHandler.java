package org.semesteroppgave.models.filehandlers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.Main;
import org.semesteroppgave.models.utilities.search.ComponentSearch;
import org.semesteroppgave.models.data.components.Component;
import org.semesteroppgave.models.filehandlers.fileOpen.FileOpener;
import org.semesteroppgave.models.filehandlers.fileOpen.FileOpenerCsv;
import org.semesteroppgave.models.filehandlers.fileOpen.FileOpenerJobj;
import org.semesteroppgave.models.utilities.alerts.Dialogs;
import org.semesteroppgave.models.filehandlers.fileSave.*;

import java.io.File;
import java.io.IOException;

/**
 * Klasse som tar seg av filhåndering for både .jobj og.csv - filer
 */

public class FileHandler {

    private enum DialogMode {
        JOBJ,
        CSV,
    }

    public static void openFileCsv() {
        File selectedFile = getFileFromFileChooserOpen(DialogMode.CSV);

        if (selectedFile != null) {
            String fileExt = getFileExt(selectedFile);
            FileOpener opener = null;

            if (".csv".equals(fileExt)) {
                opener = new FileOpenerCsv();
            } else {
                Dialogs.showErrorDialog("Fil", "Feil ved åpning av fil",
                        "Du kan bare åpne csv filer som bruker");
            }

            if (opener != null) {
                try {
                    opener.open(selectedFile.toPath());
                } catch (IOException e) {
                    Dialogs.showErrorDialog("Fil", "Feil i åpneing av fil",
                            "Åpning av filen feilet. Grunn: " + e.getMessage());
                }
            }
        }
    }

    public static void openFileCvsLaunch() {
        File loadFile = getFile("onApplicationLaunch/produkter.csv");
        FileOpener opener = new FileOpenerCsv();

        try {
            opener.open(loadFile.toPath());
        } catch (IOException e) {
            Dialogs.showErrorDialog("Fil", "Åpning av filen gikk galt", "Grunn: " + e.getMessage());
        }
    }

    public static void saveFileCsv() {
        File selectedFile = getFileFromFileChooserSave(DialogMode.CSV);

        if (selectedFile != null) {
            String fileExt = getFileExt(selectedFile);
            FileSaver saver = null;

            if (".csv".equals(fileExt)) {
                saver = new FileSaverCsv();
            } else {
                Dialogs.showErrorDialog("Lagring til fil", "Feil i lagring til fil",
                        "Du kan bare lagre til csv filer.");
            }

            if (saver != null) {
                try {
                    saver.save(selectedFile.toPath());
                    Dialogs.showSuccessDialog("Fil", "Listen ble korrekt lagret til fil",
                            "Registeret ble lagret!");
                } catch (IOException e) {
                    Dialogs.showErrorDialog("Oups", "Lagring til fil gikk galt",
                            "Grunn: " + e.getMessage());
                }
            }
        }
    }

    public static void saveFileCsvOnProgramExit(){
        File savefile = getFile("onApplicationExit/lagredeProdukter.csv");
        FileSaver saver = new FileSaverCsv();

        try {
            saver.save(savefile.toPath());
            System.out.println("Produktene ble lagret til fil");
        } catch (IOException e) {
            Dialogs.showErrorDialog("Fil", "Lagring av filen gikk galt", "Grunn: " + e.getMessage());
        }
    }

    public static String getOpenFileJobj(){
        return String.valueOf(getFileFromFileChooserOpen(DialogMode.JOBJ));
    }

    public static void openFileJobjThread(String filepath) {

        File loadFile = new File(filepath);
        FileOpener opener = new FileOpenerJobj();

        try {
            opener.open(loadFile.toPath());
        } catch (IOException e) {
            Dialogs.showErrorDialog("Fil", "Åpning av jobj-filen i tråd gikk galt",
                    "Grunn: " + e.getMessage());
        }
    }

    public static void saveFileJobj(ComponentSearch componentSearch) {
        //Liste som lagrer orginallisten i tilfelle admin lagrer et søk
        ObservableList<Component> originalList = FXCollections.observableArrayList();

        originalList.addAll(ApplicationData.getInstance().getRegisterComponent().getComponentList());
        File selectedFile = getFileFromFileChooserSave(DialogMode.JOBJ);

        //Hvis admin lagrer etter et søk, så blir søkelisten lagret
        if (!componentSearch.getSearchResult().isEmpty()){
            ApplicationData.getInstance().getRegisterComponent().setComponentList(componentSearch.getSearchResult());
        }

        if (selectedFile != null) {
            String fileExt = getFileExt(selectedFile);
            FileSaver saver = null;

            if (".jobj".equals(fileExt)) {
                saver = new FileSaverJobj();
            } else {
                Dialogs.showErrorDialog("Fil", "Feil i lagring til fil",
                        "Du kan bare lagre til jobj filer.");
            }

            if (saver != null) {
                try {
                    saver.save(selectedFile.toPath());
                    Dialogs.showSuccessDialog("Fil", "Listen ble korrekt lagret til jobj-fil",
                            "Registeret ble lagret!");
                } catch (IOException e) {
                    Dialogs.showErrorDialog("Oups", "Lagring til fil gikk galt",
                            "Grunn: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            //Gjennoppretter orginallisten
            ApplicationData.getInstance().getRegisterComponent().setComponentList(originalList);
        }
    }

    public static void saveFileJobjOnProgramExit(){
        File savefile = getFile("onApplicationExit/lagredeKomponenter.jobj");
        FileSaver saver = new FileSaverJobj();

        try {
            saver.save(savefile.toPath());
            System.out.println("Komponentene ble lagret til fil");
        } catch (IOException e) {
            Dialogs.showErrorDialog("Fil", "Lagring av filen gikk galt", "Grunn: " + e.getMessage());
        }
    }

    private static File getFileFromFileChooserSave(DialogMode mode) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Velg fil");
        fileChooser.setInitialDirectory(getFile("onApplicationRunning"));

        if (mode == DialogMode.JOBJ) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Serialized files", "*.jobj"));
        } else {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Csv files", "*.csv"));
        }
        return fileChooser.showSaveDialog(Main.getScene().getWindow());
    }

    private static File getFileFromFileChooserOpen(DialogMode mode) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Velg fil");
        fileChooser.setInitialDirectory(getFile(""));

        if (mode == DialogMode.JOBJ) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Serialized files", "*.jobj"));
        } else {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Csv files", "*.csv"));
        }
        return fileChooser.showOpenDialog(Main.getScene().getWindow());
    }

    private static String getFileExt(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf('.'));
    }

    public static File getFile(String filepath){
        return new File("src/main/resources/org/semesteroppgave/files/" + filepath);
    }

}
