package org.semesteroppgave.models.filehandlers.fileOpen;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileOpenerCsvTest {

    @Test
    void open() {
        FileOpener fileOpener = new FileOpenerCsv();
        try {
            fileOpener.open(Paths.get("files/testFiles/testOpenCsv.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}