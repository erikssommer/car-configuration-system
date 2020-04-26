package org.semesteroppgave.models.filehandlers.fileSave;

import org.junit.jupiter.api.Test;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.Main;
import org.semesteroppgave.models.data.productcomponents.Motor;

import java.io.IOException;
import java.nio.file.Paths;

class FileSaverJobjTest {

    @Test
    void save() {
        ApplicationData.getInstance().getRegisterComponent().setComponentList(
                new Motor("V12", 12300.0, "Denne V12 motoren er slitesterk og har en veldig høy ytelse")
        );

        FileSaver fileSaver = new FileSaverJobj();
        try {
            fileSaver.save(Paths.get(Main.class.getResource("files/testFiles/testWriteJobj.jobj").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}