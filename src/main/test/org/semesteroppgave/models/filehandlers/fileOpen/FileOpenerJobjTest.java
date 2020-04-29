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
            fileOpener.open(Paths.get(String.valueOf(FileHandler.getFile("testFiles/testOpenJobj.jobj"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}