package org.semesteroppgave.models.filehandlers.fileOpen;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * Test for åpning av serialisert jobj-fil
 */

class FileOpenerJobjTest {

    @Test
    void open() {
        FileOpener fileOpener = new FileOpenerJobj();
        File file = new File(getClass().getResource("/org/semesteroppgave/files/testFiles/testOpenJobj.jobj").getFile());
        try {
            fileOpener.open(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}