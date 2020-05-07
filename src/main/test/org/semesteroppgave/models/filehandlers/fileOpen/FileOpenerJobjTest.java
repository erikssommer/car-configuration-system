package org.semesteroppgave.models.filehandlers.fileOpen;

import org.junit.jupiter.api.Test;
import org.semesteroppgave.models.filehandlers.FileHandler;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Test for åpning av serialisert jobj-fil
 */

class FileOpenerJobjTest {

    @Test
    void open() {
        FileOpener fileOpener = new FileOpenerJobj();
        try {
            fileOpener.open(Paths.get(getClass().getResource("/org/semesteroppgave/files/testFiles/testOpenJobj.jobj").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}