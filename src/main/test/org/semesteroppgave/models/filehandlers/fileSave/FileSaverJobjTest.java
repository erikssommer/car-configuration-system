package org.semesteroppgave.models.filehandlers.fileSave;

import org.junit.jupiter.api.Test;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.models.data.components.Motor;

import java.io.File;
import java.io.IOException;

/**
 * Test for lagring av serialisert jobj-fil
 */

class FileSaverJobjTest {

    @Test
    void save() {
        ApplicationData.getInstance().getRegisterComponent().setComponentList(
                new Motor("V12", 12300.0, "Denne V12 motoren er slitesterk og har en veldig høy ytelse")
        );

        FileSaver fileSaver = new FileSaverJobj();
        File file = new File(getClass().getResource("/org/semesteroppgave/files/testFiles/testWriteJobj.jobj").getFile());
        try {
            fileSaver.save(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}