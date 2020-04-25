package org.semesteroppgave.models.filehandlers.fileSave;

import org.junit.jupiter.api.Test;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.models.data.carcomponents.Motor;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileSaverJobjTest {

    @Test
    void save() {
        ApplicationData.getInstance().getRegisterComponent().setComponentsList(
                new Motor("V12", 12300.0, "Denne V12 motoren er slitesterk og har en veldig høy ytelse")
        );

        FileSaver fileSaver = new FileSaverJobj();
        try {
            fileSaver.save(Paths.get("files/testFiles/testWriteJobj.jobj"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}