package org.semesteroppgave;

import javafx.stage.FileChooser;
import org.semesteroppgave.gui.Dialogs;
import org.semesteroppgave.io.fileOpen.FileOpener;
import org.semesteroppgave.io.fileOpen.FileOpenerCsv;
import org.semesteroppgave.io.fileOpen.FileOpenerJobj;
import org.semesteroppgave.io.fileSave.FileSaver;
import org.semesteroppgave.io.fileSave.FileSaverCsv;
import org.semesteroppgave.io.fileSave.FileSaverJobj;

import java.io.File;
import java.io.IOException;

public class FileHandler {

    private enum DialogMode {
        Admin,
        User,
        Save,
        Open,
    }

    public static void openFileCvs() {
        File loadFile = new File("Files/produkter.csv");
        FileOpener opener = new FileOpenerCsv();

        try {
            opener.open(loadFile.toPath());
        } catch (IOException e) {
            Dialogs.showErrorDialog("Filbehandling","Åpning av filen gikk galt","Grunn: " + e.getMessage());
        }
    }

    public static void saveFileCvs() {
        File selectedFile = getFileFromFileChooser(DialogMode.User);

        if (selectedFile != null) {
            String fileExt = getFileExt(selectedFile);
            FileSaver saver = null;

            if (".csv".equals(fileExt)) {
                saver = new FileSaverCsv();
            } else {
                Dialogs.showErrorDialog("Lagring til fil", "Feil i lagring til fil", "Du kan bare lagre til csv filer.");
            }

            if(saver != null) {
                try {
                    saver.save(selectedFile.toPath());
                    Dialogs.showSuccessDialog("Fil","Listen ble korrekt lagret til fil","Registeret ble lagret!");
                } catch (IOException e) {
                    Dialogs.showErrorDialog("Oups","Lagring til fil gikk galt", "Grunn: " + e.getMessage());
                }
            }
        }
    }

    public static void openFileJobj() {

        File loadFile = new File("Files/components.jobj");
        FileOpener opener = new FileOpenerJobj();

        try {
            opener.open(loadFile.toPath());
        } catch (IOException e) {
                Dialogs.showErrorDialog("Filbehandling","Åpning av filen gikk galt","Grunn: " + e.getMessage());
        }
    }

    public static void saveFileJobj(){
        File selectedFile = getFileFromFileChooser(DialogMode.Admin);

        if (selectedFile != null) {
            String fileExt = getFileExt(selectedFile);
            FileSaver saver = null;

            if (".jobj".equals(fileExt)) {
                saver = new FileSaverJobj();
            } else {
                Dialogs.showErrorDialog("Lagring til fil", "Feil i lagring til fil", "Du kan bare lagre til jobj filer.");
            }

            if(saver != null) {
                try {
                    saver.save(selectedFile.toPath());
                    Dialogs.showSuccessDialog("Fil","Listen ble korrekt lagret til jobj-fil","Registeret ble lagret!");
                } catch (IOException e) {
                    Dialogs.showErrorDialog("Oups","Lagring til fil gikk galt", "Grunn: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

    }

    private static File getFileFromFileChooser(DialogMode mode) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Velg fil");

        if(mode == DialogMode.Admin) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Serialized files", "*.jobj"));
        } else {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Csv files", "*.csv"));
        }
        return fileChooser.showSaveDialog(Main.getScene().getWindow());
    }

    private static String getFileExt(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf('.'));
    }

}
