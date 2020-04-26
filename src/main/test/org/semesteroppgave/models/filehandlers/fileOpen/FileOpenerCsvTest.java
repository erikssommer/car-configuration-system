package org.semesteroppgave.models.filehandlers.fileOpen;

import org.junit.jupiter.api.Test;
import org.semesteroppgave.Main;

import java.io.IOException;
import java.nio.file.Paths;

class FileOpenerCsvTest {

    @Test
    void open() {
        FileOpener fileOpener = new FileOpenerCsv();
        try {
            fileOpener.open(Paths.get(Main.class.getResource("files/testFiles/testOpenCsv.csv").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}