package org.semesteroppgave.models.filehandlers.fileOpen;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

class FileOpenerCsvTest {

    @Test
    void open() {
        FileOpener fileOpener = new FileOpenerCsv();
        try {
            fileOpener.open(Paths.get("src/main/resources/org/semesteroppgave/files/testFiles/testOpenCsv.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}