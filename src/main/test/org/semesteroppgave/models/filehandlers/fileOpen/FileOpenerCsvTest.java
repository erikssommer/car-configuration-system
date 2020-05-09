package org.semesteroppgave.models.filehandlers.fileOpen;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * Test for åpning av csv-fil
 */

class FileOpenerCsvTest {

    @Test
    void open() {
        FileOpener fileOpener = new FileOpenerCsv();
        File file = new File(getClass().getResource("/org/semesteroppgave/files/testFiles/testOpenCsv.csv").getFile());
        try {
            fileOpener.open(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}