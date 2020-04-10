package org.semesteroppgave;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.semesteroppgave.gui.Dialogs;
import org.semesteroppgave.io.*;

import java.io.File;
import java.io.IOException;

public class FileHandler {

    private enum DialogMode {
        Admin,
        User,
    }
    /*
    static void openFileCvs(Stage stage, RegisterProduct register) {
        File selectedFile = getFileFromFileChooser(DialogMode.User, stage);

        if (selectedFile != null) {
            String fileExt = getFileExt(selectedFile);
            FileOpener opener = null;

            if (".cvs".equals(fileExt)) {
                opener = new FileOpenerCsv();
            } else {
                Dialogs.showErrorDialog("Filbehandling","Feil i åpning av fil","Du kan bare åpne txt eller jobj filer.");
            }

            if(opener != null) {
                try {
                    opener.open(register, selectedFile.toPath());
                } catch (IOException e) {
                    Dialogs.showErrorDialog("Filbehandling","Åpning av filen gikk galt","Grunn: " + e.getMessage());
                }
            }
        }
    }

    static void saveFileCvs(Stage stage, RegisterProduct register) {
        File selectedFile = getFileFromFileChooser(DialogMode.User, stage);

        if (selectedFile != null) {
            String fileExt = getFileExt(selectedFile);
            FileSaver saver = null;

            switch (fileExt) {
                case ".cvs" : saver = new FileSaverCsv(); break;
                case ".jobj" : saver = new FileSaverJobj(); break;
                default : Dialogs.showErrorDialog("Lagring til fil","Feil i lagring til fil", "Du kan bare lagre til enten cvs eller jobj filer.");
            }

            if(saver != null) {
                try {
                    saver.save(register, selectedFile.toPath());
                    Dialogs.showSuccessDialog("Fil","Listen ble korrekt lagret til fil","Registeret ble lagret!");
                } catch (IOException e) {
                    Dialogs.showErrorDialog("Oups","Lagring til fil gikk galt", "Grunn: " + e.getMessage());
                }
            }
        }
    }

     */

    public static void openFileJobj(RegisterComponent register) {

        File loadFile = new File("Files/component.jobj");
        FileOpener opener = new FileOpenerJobj();

        try {
            opener.open(register, loadFile.toPath());
        } catch (IOException e) {
                Dialogs.showErrorDialog("Filbehandling","Åpning av filen gikk galt","Grunn: " + e.getMessage());
        }
    }

    public static void saveFileJobj(RegisterComponent register){
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
                    saver.save(register, selectedFile.toPath());
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
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Cvs files", "*.cvs"));
        }
        return fileChooser.showSaveDialog(Main.getScene().getWindow());
    }

    private static String getFileExt(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf('.'));
    }

}
