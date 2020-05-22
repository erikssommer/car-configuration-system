package org.semesteroppgave.models.filehandlers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.Main;
import org.semesteroppgave.models.data.components.Component;
import org.semesteroppgave.models.filehandlers.fileOpen.FileOpener;
import org.semesteroppgave.models.filehandlers.fileOpen.FileOpenerCsv;
import org.semesteroppgave.models.filehandlers.fileOpen.FileOpenerJobj;
import org.semesteroppgave.models.filehandlers.fileSave.FileSaver;
import org.semesteroppgave.models.filehandlers.fileSave.FileSaverCsv;
import org.semesteroppgave.models.filehandlers.fileSave.FileSaverJobj;
import org.semesteroppgave.models.utilities.alerts.Dialogs;
import org.semesteroppgave.models.utilities.search.ComponentSearch;

import java.io.File;
import java.io.IOException;

import static org.semesteroppgave.Main.FOLDERNAME;
import static org.semesteroppgave.Main.folderCreated;

/**
 * Class that handles both .jobj and .csv files
 */

public class FileHandler {

    private enum FileMode {
        JOBJ,
        CSV,
    }

    private enum DialogMode {
        OPEN,
        SAVE,
    }

    public static void openFileCsv() {
        File selectedFile = getFileFromFileChooser(FileMode.CSV, DialogMode.OPEN);

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

    public static void openFileCvsLaunch(String filePath) {
        File file = new File(filePath);
        FileOpener opener = new FileOpenerCsv();
        try {
            opener.open(file.toPath());
        } catch (IOException e) {
            Dialogs.showErrorDialog("Fil", "Åpning av filen gikk galt", "Grunn: " + e.getMessage());
        }
    }

    public static void saveFileCsv() {
        File selectedFile = getFileFromFileChooser(FileMode.CSV, DialogMode.SAVE);

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

    public static void saveFileCsvOnProgramExit(String filepath){
        FileSaver saver = new FileSaverCsv();
        File file = new File(filepath);

        try {
            saver.save(file.toPath());
            System.out.println("Produktene ble lagret til fil");
        } catch (IOException e) {
            Dialogs.showErrorDialog("Fil", "Lagring av filen gikk galt", "Grunn: " + e.getMessage());
        }
    }

    public static String getOpenFileJobj(){
        return String.valueOf(getFileFromFileChooser(FileMode.JOBJ, DialogMode.OPEN));
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
        //List that stores the original list in case admin stores a query
        ObservableList<Component> originalList = FXCollections.observableArrayList();

        originalList.addAll(ApplicationData.getInstance().getRegisterComponent().getComponentList());
        File selectedFile = getFileFromFileChooser(FileMode.JOBJ, DialogMode.SAVE);

        //If admin saves after a search, then the search list is saved
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
            //Restores the original list
            ApplicationData.getInstance().getRegisterComponent().setComponentList(originalList);
        }
    }

    public static void saveFileJobjOnProgramExit(String filepath){
        FileSaver saver = new FileSaverJobj();
        File file = new File(filepath);

        try {
            saver.save(file.toPath());
            System.out.println("Komponentene ble lagret til fil");
        } catch (IOException e) {
            Dialogs.showErrorDialog("Fil", "Lagring av filen gikk galt", "Grunn: " + e.getMessage());
        }
    }

    private static File getFileFromFileChooser(FileMode file, DialogMode mode) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Velg fil");
        //Tests whether a folder has been created
        if (folderCreated){
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + File.separator + FOLDERNAME));
        }

        if (file == FileMode.JOBJ) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Serialized files", "*.jobj"));
        } else {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Csv files", "*.csv"));
        }

        if (mode == DialogMode.OPEN){
            return fileChooser.showOpenDialog(Main.getScene().getWindow());
        }else {
            return fileChooser.showSaveDialog(Main.getScene().getWindow());
        }
    }

    private static String getFileExt(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf('.'));
    }
}
