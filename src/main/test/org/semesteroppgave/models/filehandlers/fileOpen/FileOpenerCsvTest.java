package org.semesteroppgave.models.filehandlers.fileOpen;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Test for åpning av csv-fil
 */

class FileOpenerCsvTest {

    @Test
    void open() {
        FileOpener fileOpener = new FileOpenerCsv();
        try {
            fileOpener.open(Paths.get(getClass().getResource("/org/semesteroppgave/files/testFiles/testOpenCsv.csv").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}