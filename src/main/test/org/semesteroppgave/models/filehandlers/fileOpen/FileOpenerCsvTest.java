package org.semesteroppgave.models.filehandlers.fileOpen;

import org.junit.jupiter.api.Test;
import org.semesteroppgave.models.filehandlers.FileHandler;

import java.io.IOException;
import java.nio.file.Paths;

class FileOpenerCsvTest {

    @Test
    void open() {
        FileOpener fileOpener = new FileOpenerCsv();
        try {
            fileOpener.open(Paths.get(String.valueOf(FileHandler.getFile("testFiles/testOpenCsv.csv"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}